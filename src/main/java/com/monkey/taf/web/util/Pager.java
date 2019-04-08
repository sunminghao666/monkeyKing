package com.monkey.taf.web.util;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import com.monkey.taf.common.Tools;

/**
 * 
 * @模块名：module_taf
 * @包名：com.tit.taf.web.util
 * @类名称： Pager
 * @类描述：【类描述】分页封装类
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2018年9月19日上午9:40:35
 * @param <T>
 */
public class Pager < T > {

    /** 默认每页显示数量 */
    public final static int DEFAULT_SIZE = 10;

    private int pageSize;

    private int pageNo;

    private int firstEntityIndex;

    private int lastEntityIndex;

    private Collection < T > entities;

    private int entityCount;

    private int pageCount;

    public Pager() {
    }

    /**
     * 获取数据路径
     */
    private String path;

    /**
     * @param pageSize 每页记录数
     * @param pageNo 页号
     */
    public Pager(int pageSize, int pageNo) {
        if (pageNo > 1 && pageSize <= 0) {
            throw new IllegalArgumentException("Illegal paging arguments. [pageSize=" + pageSize + ", pageIndex="
                    + pageNo + "]");
        }

        if (pageSize < 0)
            pageSize = 0;
        if (pageNo < 1)
            pageNo = 1;

        this.pageSize = pageSize;
        this.pageNo = pageNo;
        firstEntityIndex = (pageNo - 1) * pageSize;
        lastEntityIndex = pageNo * pageSize;
    }

    /**
     * @param pageSize 每页记录数
     * @param pageNo 页号
     */
    public Pager(String path, int pageSize, int pageNo) {
        if (pageNo > 1 && pageSize <= 0) {
            throw new IllegalArgumentException("Illegal paging arguments. [pageSize=" + pageSize + ", pageIndex="
                    + pageNo + "]");
        }

        this.path = path;

        if (pageSize < 0)
            pageSize = 0;
        if (pageNo < 1)
            pageNo = 1;

        this.pageSize = pageSize;
        this.pageNo = pageNo;
        firstEntityIndex = (pageNo - 1) * pageSize;
        lastEntityIndex = pageNo * pageSize;
    }

    /**
     * 返回每一页的大小，即每页的记录数。
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 返回要提取的页的序号，该序号是从1开始计算的。
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * 返回当前页中第一条记录对应的序号，该序号是从0开始计算的。<br>
     * 注意，此处在计算firstEntityIndex是不考虑实际提取过程中当前页是否存在的。
     */
    public int getFirstEntityIndex() {
        return firstEntityIndex;
    }

    public void setFirstEntityIndex(int firstEntityIndex) {
        this.firstEntityIndex = firstEntityIndex;
    }

    public void setLastEntityIndex(int lastEntityIndex) {
        this.lastEntityIndex = lastEntityIndex;
    }

    /**
     * 返回当前页中最后一条记录对应的序号，该序号是从0开始计算的。<br>
     * 注意，此处在计算lastEntityIndex是不考虑实际提取过程中当前页是否存在或者记录数是否可达到pageSize的。
     */
    public int getLastEntityIndex() {
        return lastEntityIndex;
    }

    /**
     * 设置当页数据。
     */
    public void setEntities(Collection < T > entities) {
        this.entities = entities;
    }

    /**
     * 返回当页数据。
     */
    @SuppressWarnings("unchecked")
    public Collection < T > getEntities() {
        return (entities != null) ? entities : Collections.EMPTY_LIST;
    }

    /**
     * 设置总记录数。
     * <p>
     * 此处的总记录数并不是指当页数据的总数，而是指整个结果的总数。 即每一页数据累计的总数。
     * </p>
     */
    public int getEntityCount() {
        return entityCount;
    }

    /**
     * 返回总记录数。
     * <p>
     * 此处的总记录数并不是指当页数据的总数，而是指整个结果的总数。即每一页数据累计的总数。
     * </p>
     */
    public void setEntityCount(int entityCount) {
        if (entityCount < 0) {
            throw new IllegalArgumentException("Illegal entityCount arguments. [entityCount=" + entityCount + "]");
        }

        this.entityCount = entityCount;
        pageCount = ((entityCount - 1) / pageSize) + 1;
    }

    /**
     * 返回总的记录页数。
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * 返回请求路径
     */
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 返回当页数据的迭代器。
     */
    public Iterator < T > iterator() {
        if (entities != null) {
            return entities.iterator();
        }
        else {
            return null;
        }
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * 
     * @方法名：pageParams
     * @方法描述【方法功能描述】初始化分页实体及参数
     * @param status
     * @param basicDto
     * @return
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年7月26日 下午3:27:19
     * @修改人：cc
     * @修改时间：2018年7月26日 下午3:27:19
     */
    public static Pager pageParams(Status status, BasicDto basicDto) {
        if (Tools.isBlank(basicDto.getBody().get("pageSize")) || Tools.isBlank(basicDto.getBody().get("pageNo"))) {
            return null;
        }
        else if (Integer.parseInt(basicDto.getBody().get("pageSize") + "") <= 0) {
            return null;
        }
        else if (Integer.parseInt(basicDto.getBody().get("pageNo") + "") <= 0) {
            return null;
        }
        else {
            Pager pager = new Pager(Integer.parseInt(basicDto.getBody().get("pageSize") + ""),
                    Integer.parseInt(basicDto.getBody().get("pageNo") + ""));
            basicDto.getBody().put("firstEntityIndex", pager.getFirstEntityIndex());
            basicDto.getBody().put("lastEntityIndex", pager.getLastEntityIndex());
            return pager;
        }
    }
}
