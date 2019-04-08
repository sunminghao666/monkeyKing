package com.monkey.auth.manage.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monkey.auth.manage.dao.ResourcesMapper;
import com.monkey.auth.manage.dao.RoleResourceMapper;
import com.monkey.auth.manage.pojo.Resources;
import com.monkey.auth.manage.pojo.ResourcesExample;
import com.monkey.auth.manage.pojo.RoleResourceExample;
import com.monkey.auth.manage.service.ResourcesService;
import com.monkey.db.base.dao.PwbDictionaryMapper;
import com.monkey.db.base.pojo.PwbDictionary;
import com.monkey.taf.common.Tools;
import com.monkey.taf.log.TAFLog;
import com.monkey.taf.web.util.Pager;

/**
 * 
 * @模块名：module_auth
 * @包名：com.tit.gfb.manage.service.impl
 * @类名称： ResourcesServiceImpl
 * @类描述：【类描述】资源管理模块
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2019年2月26日下午3:26:06
 */
@Service(value = "resourcesService")
public class ResourcesServiceImpl implements ResourcesService {

    @Autowired
    ResourcesMapper resourcesMapper;

    @Autowired
    RoleResourceMapper roleResourceMapper;

    @Autowired
    PwbDictionaryMapper pwbDictionaryMapper;

    @Override
    public Resources addResources(Resources resource) {
        // 添加新增时间
        resource.setInsertTime(new Date());
        resourcesMapper.insertSelective(resource);
        return resource;
    }

    @Override
    public Resources findResourcesById(Long id) throws Exception {
        return resourcesMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateResources(Resources resources) throws Exception {
        // 添加修改时间
        resources.setUpdateTime(new Date());
        return resourcesMapper.updateByPrimaryKeySelective(resources);
    }

    @Override
    public void deleteResources(List < Long > listResourcesId) throws Exception {
        for (int i = 0; i < listResourcesId.size(); i++) {
            resourcesMapper.deleteByPrimaryKey(listResourcesId.get(i));
            RoleResourceExample roleResourceExample = new RoleResourceExample();
            RoleResourceExample.Criteria criteria = roleResourceExample.createCriteria();
            criteria.andResourceCodeEqualTo(listResourcesId.get(i) + "");
            roleResourceMapper.deleteByExample(roleResourceExample);
        }
    }

    @Override
    public Pager < Resources > queryByPage(String resourceCode, String resourceName, int size, int pageIndex) {
        try {
            Pager < Resources > p = new Pager < Resources >();
            int count = resourcesMapper.countResource(resourceCode, resourceName);
            int pageCount = count % size == 0 ? count / size : (1 + count / size);

            if (pageCount == 0) {
                pageIndex = 1;
            }
            else if (pageIndex > pageCount) {
                pageIndex = pageCount;
            }
            else if (pageIndex < 1) {
                pageIndex = 1;
            }
            int startIndex = (pageIndex - 1) * size;

            List < Resources > resList = resourcesMapper.queryByPage(resourceCode, resourceName, size, startIndex);
            List < PwbDictionary > enabledList = pwbDictionaryMapper.selectByType("enabled");// 启用标记列表
            for (int i = 0; i < resList.size(); i++) {
                if (!Tools.isBlank(resList.get(i).getParentId())) {
                    Resources res = this.findResourcesById(Long.parseLong(resList.get(i).getParentId()));
                    if (res != null) {
                        resList.get(i).setParentName(res.getResourceName());
                    }
                }

                if (!Tools.isBlank(resList.get(i).getEnabled())) {
                    for (int j = 0; j < enabledList.size(); j++) {
                        if (resList.get(i).getEnabled().equals(enabledList.get(j).getDicCode())) {
                            resList.get(i).setEnabled(enabledList.get(j).getDicValue());
                        }
                    }
                }

            }
            p.setEntities(resList);
            p.setPageSize(size);
            p.setPageNo(pageIndex);
            p.setEntityCount(count);
            p.setPageCount(pageCount);

            return p;
        }
        catch (Exception e) {
            TAFLog.error("分页查询错误", e);
            return null;
        }

    }

    @Override
    public List < Resources > findResourcesByParentId(int parentId) throws Exception {
        ResourcesExample resourcesExample = new ResourcesExample();
        ResourcesExample.Criteria criteria = resourcesExample.createCriteria();
        criteria.andParentIdEqualTo(parentId+"");
        criteria.andEnabledEqualTo("1");
        return resourcesMapper.selectByExample(resourcesExample);
    }

    @Override
    public List < Resources > findResourcesFirstClass() throws Exception {
        ResourcesExample resourcesExample = new ResourcesExample();
        ResourcesExample.Criteria criteria = resourcesExample.createCriteria();
        criteria.andParentIdIsNotNull();
        criteria.andEnabledEqualTo("1");
        return resourcesMapper.selectByExample(resourcesExample);
    }

    @Override
    public List < Resources > findResourcesByCondition(Resources resource) throws Exception {
        return resourcesMapper.findResourcesByCondition(resource);
    }

    @Override
    public List < Resources > findCurrUserAuthRes(Long id) throws Exception {
        return resourcesMapper.findCurrUserAuthRes(id);
    }

    @Override
    public List < Resources > findAllResource() throws Exception {

        ResourcesExample resourcesExample = new ResourcesExample();
        ResourcesExample.Criteria criteria = resourcesExample.createCriteria();
        criteria.andEnabledEqualTo("1");
        return resourcesMapper.selectByExample(resourcesExample);
    }

    @Override
    public List < Resources > findResourcesByCondition2(Resources resource) throws Exception {
        return resourcesMapper.findResourcesByCondition2(resource);
    }
}
