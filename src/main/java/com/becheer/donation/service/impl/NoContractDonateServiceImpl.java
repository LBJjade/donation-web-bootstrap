package com.becheer.donation.service.impl;

import com.becheer.donation.dao.NoContractDonateMapper;
import com.becheer.donation.model.extension.contract.NoContractDonateExtension;
import com.becheer.donation.service.INoContractDonateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/*
* NoContractDonateServiceImpl 无合同捐赠Service
* Creator : xiaokepu
* Date : 2017-09-20
*/
@Service
public class NoContractDonateServiceImpl implements INoContractDonateService {

    @Resource
    private NoContractDonateMapper noContractDonateMapper;

    @Override
    public List<NoContractDonateExtension> GetRecentNoContractDonate(int num) {
        List<NoContractDonateExtension> result= noContractDonateMapper.SelectRecentNoContractDonate(num);
        return result;
    }

    @Override
    public List<NoContractDonateExtension> GetRecentNoContractDonate(long projectId,int num) {
        List<NoContractDonateExtension> result= noContractDonateMapper.SelectRecentNoContractDonateByProject(projectId,num);
        return result;
    }
}
