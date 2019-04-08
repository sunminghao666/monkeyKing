package com.monkey.auth.manage.service;

import java.util.List;

import com.monkey.auth.manage.pojo.Resources;
import com.monkey.taf.web.util.Pager;

/**
 * 
 * @模块名：module_auth
 * @包名：com.tit.auth.manage.service
 * @类名称： ResourcesService
 * @类描述：【类描述】资源管理
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2019年2月27日上午11:11:28
 */
public interface ResourcesService {

    /**
     * 
     * @方法名：addResources
     * @方法描述【方法功能描述】新增资源
     * @param resource
     * @return
     * @throws Exception
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2019年2月27日 上午11:11:46
     * @修改人：cc
     * @修改时间：2019年2月27日 上午11:11:46
     */
    public Resources addResources(Resources resource) throws Exception;

    /**
     * 分页查询资源
     * 
     * @param resourceCode
     * @param resourceName
     * @param size
     * @param pageIndex
     * @return
     * @throws ServiceException
     */
    public Pager < Resources > queryByPage(String resourceCode, String resourceName, int size, int pageIndex);

    /**
     * 根据Id查询资源
     * 
     * @param id
     * @return
     * @throws Exception
     */
    public Resources findResourcesById(Long id) throws Exception;

    /**
     * 更新资源
     * 
     * @param resources
     * @return
     * @throws Exception
     */
    public int updateResources(Resources resources) throws Exception;

    /**
     * 资源删除
     * 
     * @param listResourcesId
     * @return
     * @throws Exception
     */
    public void deleteResources(List < Long > listResourcesId) throws Exception;

    /**
     * 查询父资源下的子资源
     * 
     * @param parentId
     * @return
     * @throws Exception
     */
    public List < Resources > findResourcesByParentId(int parentId) throws Exception;

    /**
     * 根据编码名称查询资源
     * 
     * @param resource
     * @return
     * @throws Exception
     */
    List < Resources > findResourcesByCondition(Resources resource) throws Exception;

    List < Resources > findResourcesByCondition2(Resources resource) throws Exception;

    /**
     * 
     * 查询一级菜单资源
     * 
     * @param
     * @since 2015-7-20 下午3:48:44
     * @author cc
     * @return
     */
    public List < Resources > findResourcesFirstClass() throws Exception;

    /**
     * 查询当前用户下的所有资源
     * 
     * @param id
     * @return
     * @throws Exception
     */
    List < Resources > findCurrUserAuthRes(Long id) throws Exception;

    /**
     * 查询所有资源
     * 
     * @param id
     * @return
     * @throws Exception
     */
    List < Resources > findAllResource() throws Exception;
}
