package com.becheer.donation.service.impl;

/*
* MemberService
* Creator : xiaokepu
* Date : 2017-09-14
*/

import com.becheer.donation.configs.OssConfig;
import com.becheer.donation.dao.AccepterMapper;
import com.becheer.donation.dao.AreaMapper;
import com.becheer.donation.dao.MemberMapper;
import com.becheer.donation.dao.ProgressMapper;
import com.becheer.donation.model.Member;
import com.becheer.donation.model.Progress;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.member.MemberInfoExtension;
import com.becheer.donation.service.IMemberService;
import com.becheer.donation.strings.Message;
import com.becheer.donation.utils.HashUtil;
import com.becheer.donation.utils.OssUtil;
import com.becheer.donation.utils.RedisUtil;
import com.becheer.donation.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class MemberServiceImpl implements IMemberService {

    @Resource
    private MemberMapper memberMapper;

    @Resource
    private AccepterMapper accepterMapper;

    @Resource
    private AreaMapper areaMapper;

    @Resource
    private ProgressMapper progressMapper;

    @Autowired
    OssConfig ossConfig;

    @Override
    public ResponseDto SubmitRegister(String mobile, String pwd, int role) {
        Member member = new Member();
        member.setMobile(mobile);
        member.setRole(role);
        member.setPassword(pwd);
        member.setEnable(1);
        member.setPassword(HashUtil.GetPassword(pwd));
//        member.setId(UUID.GetInt64UUID());
        int result = memberMapper.insertMember(member);
        if (result > 0) {
            member = memberMapper.SelectMemberByMobile(mobile);
            Progress progress=new Progress();
            progress.setRefTable("dnt_member");
            progress.setRefRecordId(member.getId());
            progress.setType(3);
            progress.setEnable(1);
            String title ="会员注册成功";
            progress.setTitle(title);
            progress.setContent(title);
            progress.setProgressType(3);
            progressMapper.InsertProgress(progress);
            return new ResponseDto(200, Message.REGISTER_REGISTER_SUCCESS, member);
        } else {
            return new ResponseDto(400, Message.SERVER_ERROR);
        }
    }

    @Override
    public ResponseDto Login(String mobile, String pwd) {
        Member member = memberMapper.SelectMemberByMobile(mobile);
        if (member == null) {
            return new ResponseDto(404, Message.LOGIN_MOBILE_NOT_EXIST);
        }
        if (member.getEnable() == 0) {
            return new ResponseDto(405, Message.LOGIN_ACCOUNT_DISABLED);
        }
        if (!member.getPassword().equals(HashUtil.GetPassword(pwd))) {
            return new ResponseDto(406, Message.LOGIN_PASSWORD_ERROR);
        } else {
            return new ResponseDto(407, Message.LOGIN_SUCCESS, member);
        }
    }

    @Override
    public ResponseDto GetMemberById(long memberId) {
        Member member = memberMapper.SelectMemberById(memberId);
        if (member == null) {
            return new ResponseDto(400, Message.MEMBER_NOT_EXISTS);
        }
        if (member.getEnable() == 0) {
            return new ResponseDto(401, Message.MEMBER_DISABLED);
        }

        MemberInfoExtension memberInfoExtension = new MemberInfoExtension();
        memberInfoExtension.setId(member.getId());
        memberInfoExtension.setName(member.getMemberName());
        memberInfoExtension.setRole(member.getRole());
        memberInfoExtension.setProject(member.getProject());
        memberInfoExtension.setSummary(member.getSummary());
        memberInfoExtension.setValidation(member.getValidation());
        memberInfoExtension.setAvator(member.getAvatorImg());
        memberInfoExtension.setBirthday(member.getBirthday());
        memberInfoExtension.setMobile(member.getMobile());
        memberInfoExtension.setProvince(member.getProvince());
        memberInfoExtension.setCity(member.getCity());
        memberInfoExtension.setArea(member.getArea());
        if (member.getRole() == 1) {
            //个人
            memberInfoExtension.setIdCard(StringUtil.getEncryptedIdCard(member.getIdCard()));
            memberInfoExtension.setSex(member.getSex());
//            memberInfoExtension.setBirthday(member.getBirthday());
        } else if (member.getRole() == 2) {
            //公司
            memberInfoExtension.setOrganizationType(member.getOrganizationType());
            memberInfoExtension.setOrganizationCode(member.getOrganizationCode());
            memberInfoExtension.setLicense(member.getLicense());
        } else {
            return new ResponseDto(404, Message.MEMBER_ROLE_ERROR);
        }
        return new ResponseDto(200, Message.MEMBER_GET_SUCCESS, memberInfoExtension);
    }

    @Override
    public Member GetMemberByMobile(String mobile) {
        return memberMapper.SelectMemberByMobile(mobile);
    }

    @Override
    public MemberInfoExtension GetMemberExtensionById(long memberId) {
        Member member = memberMapper.SelectMemberById(memberId);
        if (member == null) {
            return null;
        }
        MemberInfoExtension memberInfoExtension = new MemberInfoExtension();
        memberInfoExtension.setId(member.getId());
        memberInfoExtension.setName(member.getMemberName());
        memberInfoExtension.setRole(member.getRole());
        memberInfoExtension.setProject(member.getProject());
        memberInfoExtension.setSummary(member.getSummary());
        memberInfoExtension.setValidation(member.getValidation());
        memberInfoExtension.setAvator(member.getAvatorImg());
        memberInfoExtension.setBirthday(member.getBirthday());
        memberInfoExtension.setProvinceId(member.getProvinceId());
        memberInfoExtension.setCityId(member.getCityId());
        memberInfoExtension.setAreaId(member.getAreaId());

        if (member.getRole() == 1) {
            //个人
            memberInfoExtension.setMobile(member.getMobile());
            memberInfoExtension.setIdCard(member.getIdCard());
            memberInfoExtension.setSex(member.getSex());
            memberInfoExtension.setBirthday(member.getBirthday());
            memberInfoExtension.setIdCardFront(member.getIdCardFrontImg());
            memberInfoExtension.setIdCardBack(member.getIdCardBackImg());
        } else if (member.getRole() == 2) {
            //公司
            memberInfoExtension.setOrganizationType(member.getOrganizationType());
            memberInfoExtension.setOrganizationCode(member.getOrganizationCode());
            memberInfoExtension.setLicense(member.getLicense());
        }
        return memberInfoExtension;
    }

    @Override
    public ResponseDto UpdateMemberInfo(MemberInfoExtension memberInfoExtension) {
        Member member = new Member();
        member.setId(memberInfoExtension.getId());
        member.setMemberName(memberInfoExtension.getName());
        member.setSex(memberInfoExtension.getSex());
        member.setBirthday(memberInfoExtension.getBirthday());
        member.setProject(memberInfoExtension.getProject());
        member.setIdCard(memberInfoExtension.getIdCard());
        member.setSummary(memberInfoExtension.getSummary());
        member.setValidation(memberInfoExtension.getValidation());
        member.setOrganizationCode(memberInfoExtension.getOrganizationCode());
        member.setOrganizationType(memberInfoExtension.getOrganizationType());
        if (!StringUtils.isEmpty(memberInfoExtension.getIdCardFront())) {
            if (StringUtil.checkBase64FileSize(memberInfoExtension.getIdCardFront(), 2 * 1024 * 1024)) {
                return new ResponseDto(500, Message.MEMBER_IMG_OVERLIMIT);
            }
            byte[] bytes = StringUtil.base64ImgToByteArray(memberInfoExtension.getIdCardFront());
            String fileName = "fImg" + memberInfoExtension.getId();
            fileName = HashUtil.getEncryptedFileName(fileName) + ".jpg";
            fileName = ossConfig.getIdCardPath() + fileName;
            OssUtil.addByteArray(bytes, fileName);
            member.setIdCardFrontImg(fileName);
        }
        if (!StringUtils.isEmpty(memberInfoExtension.getLicense())) {
            if (StringUtil.checkBase64FileSize(memberInfoExtension.getLicense(), 2 * 1024 * 1024)) {
                return new ResponseDto(500, Message.MEMBER_IMG_OVERLIMIT);
            }
            byte[] bytes = StringUtil.base64ImgToByteArray(memberInfoExtension.getLicense());
            String fileName = "lImg" + memberInfoExtension.getId();
            fileName = HashUtil.getEncryptedFileName(fileName) + ".jpg";
            fileName = ossConfig.getLicensePath() + fileName;
            OssUtil.addByteArray(bytes, fileName);
            member.setLicense(fileName);
        }
        if (!StringUtils.isEmpty(memberInfoExtension.getIdCardBack())) {
            if (StringUtil.checkBase64FileSize(memberInfoExtension.getIdCardBack(), 2 * 1024 * 1024)) {
                return new ResponseDto(500, Message.MEMBER_IMG_OVERLIMIT);
            }
            byte[] bytes = StringUtil.base64ImgToByteArray(memberInfoExtension.getIdCardBack());
            String fileName = "bImg" + memberInfoExtension.getId();
            fileName = HashUtil.getEncryptedFileName(fileName) + ".jpg";
            fileName = ossConfig.getIdCardPath() + fileName;
            OssUtil.addByteArray(bytes, fileName);
            member.setIdCardBackImg(fileName);
        }
        Map memberArea=areaMapper.selectAreaByAreaId(memberInfoExtension.getAreaId());

        if (memberArea==null||memberArea.size()!=6){
            return new ResponseDto(400,Message.MEMBER_AREA_ERROR);
        }
        member.setProvinceId(Long.parseLong(String.valueOf(memberArea.get("pid"))));
        member.setCityId(Long.parseLong(String.valueOf(memberArea.get("cid"))));
        member.setAreaId(Long.parseLong(String.valueOf(memberArea.get("aid"))));
        member.setProvince(memberArea.get("pName").toString());
        member.setCity(memberArea.get("cName").toString());
        member.setArea(memberArea.get("aName").toString());


        int result = memberMapper.UpdateMember(member);
        if (result > 0) {
            RedisUtil.delMemberKey(member.getId());
            return new ResponseDto(200, Message.MEMBER_UPDATE_SUCCESS);
        } else {
            return new ResponseDto(500, Message.MEMBER_UPDATE_ERROR);
        }
    }

    @Override
    public ResponseDto uploadAvator(long memberId, String fileStr, boolean isAccepter) {
        try {
            byte[] bytes = StringUtil.base64ImgToByteArray(fileStr);
            String fileName = ossConfig.getAvatorPath() + "a" + memberId + ".jpg";
            OssUtil.addByteArray(bytes, fileName);
            int result = 0;
            if (!isAccepter) {
                result = memberMapper.updateAvator(fileName, memberId);
            } else {
                result = accepterMapper.updateAvator(fileName, memberId);
            }
            if (result > 0) {
                return new ResponseDto(200, Message.MEMBER_AVATOR_UPLOAD_SUCCESS, fileName);
            } else {
                return new ResponseDto(500, Message.MEMBER_AVATOR_UPLOAD_ERROR);
            }
        } catch (Exception e) {
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

    @Override
    public Member GetMember(long memberId) {
        return memberMapper.SelectMemberById(memberId);
    }

    @Override
    public int UpdatePw(String newPw, String mobile) {
        newPw = HashUtil.GetPassword(newPw);
        int result = memberMapper.UpdatePw(newPw, mobile);
        return result;
    }

    @Override
    public int updateLoginInfo(String ip, long memberId) {
        return memberMapper.updateLogin(ip, memberId);
    }
}
