package com.monkey.auth.manage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monkey.auth.manage.dao.ResourcesMapper;
import com.monkey.auth.manage.dao.RoleResourceMapper;
import com.monkey.auth.manage.pojo.Resources;
import com.monkey.auth.manage.pojo.ResourcesExample;
import com.monkey.auth.manage.pojo.RoleResource;
import com.monkey.auth.manage.pojo.RoleResourceExample;
import com.monkey.auth.manage.pojo.ZtreeDTO;
import com.monkey.auth.manage.service.RoleResourceService;

/**
 * 
 * @模块名：module_auth
 * @包名：com.tit.auth.manage.service.impl
 * @类名称： RoleResourceServiceImpl
 * @类描述：【类描述】用户角色管理
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2019年2月27日上午11:14:33
 */
@Service
public class RoleResourceServiceImpl implements RoleResourceService {
    @Autowired
    ResourcesMapper resourcesMapper;

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Override
    public List < RoleResource > findRoleResourceByRoleCode(String roleCode) throws Exception {
        RoleResourceExample roleResourceExample = new RoleResourceExample();
        RoleResourceExample.Criteria cr = roleResourceExample.createCriteria();
        cr.andResourceCodeEqualTo(roleCode);
        return this.roleResourceMapper.selectByExample(roleResourceExample);
    }

    @Override
    public void updateRoleResource(String roleId, String checked, String unChecked) throws Exception {
        String[] checkedArray = checked.split(",");
        // String[] unCheckedArray = unChecked.split(",");
        RoleResource roleResource = new RoleResource();

        RoleResourceExample roleResourceExample = new RoleResourceExample();
        RoleResourceExample.Criteria cr = roleResourceExample.createCriteria();
        cr.andResourceCodeEqualTo(roleId);
        this.roleResourceMapper.selectByExample(roleResourceExample);
        // 选中处理
        for (int i = 0; i < checkedArray.length; i++) {
            // 查询id是否存在
            RoleResourceExample roleResourceExample1 = new RoleResourceExample();
            RoleResourceExample.Criteria cr1 = roleResourceExample1.createCriteria();
            cr1.andResourceCodeEqualTo(roleId);
            cr1.andResourceCodeEqualTo(checkedArray[i]);
            List < RoleResource > u = roleResourceMapper.selectByExample(roleResourceExample1);
            if (u.size() == 0) {
                roleResource.setRoleCode(roleId);
                roleResource.setResourceCode(checkedArray[i]);
                roleResourceMapper.insertSelective(roleResource);
            }
        }

    }

    @Override
    public List < ZtreeDTO > findResourcesByRoleCode(String roleCode) throws Exception {
        List < ZtreeDTO > ztreeDTOs = new ArrayList < ZtreeDTO >();
        RoleResourceExample roleResourceExample = new RoleResourceExample();
        RoleResourceExample.Criteria cr = roleResourceExample.createCriteria();
        cr.andRoleCodeEqualTo(roleCode);
        List < RoleResource > roleResources = this.roleResourceMapper.selectByExample(roleResourceExample);
        ResourcesExample resourcesExample = new ResourcesExample();
        ResourcesExample.Criteria cr1 = resourcesExample.createCriteria();
        cr1.andEnabledEqualTo("1");
        List < Resources > allResources = resourcesMapper.selectByExample(resourcesExample);
        for (int i = 0; i < allResources.size(); i++) {
            ZtreeDTO ztreeDTO = new ZtreeDTO();
            ztreeDTO.setId(allResources.get(i).getId());
            if (allResources.get(i).getParentId() != null && !"".equals(allResources.get(i).getParentId())) {
                ztreeDTO.setpId(Long.parseLong(allResources.get(i).getParentId()));
            }
            ztreeDTO.setName(allResources.get(i).getResourceName());
            ztreeDTO.setOpen(true);
            for (int j = 0; j < roleResources.size(); j++) {
                if (roleResources.get(j).getResourceCode().equals(Long.toString(allResources.get(i).getId()))) {
                    ztreeDTO.setChecked(true);
                }
            }
            ztreeDTOs.add(ztreeDTO);
        }
        return ztreeDTOs;

    }
}
