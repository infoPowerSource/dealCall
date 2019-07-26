package com.deal.dao.common;

import com.deal.util.Page;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class BaseDao{
    @Autowired
    public SessionFactory sessionFactory;

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public Session getOpenSession(){
        return sessionFactory.openSession();
    }

    public void closeSession(Session session){
        session.flush();
        session.close();
    }

    /**
     * @param pageNum  当前页数
     * @param pageSize 每页显示的记录数
     * @return 将查询结果封装为Page返回
     * @function 根据传递过来的Object, 分页显示在数据库中与其匹配的记录
     */
    public Page findPageByCriteria(int pageNum, int pageSize, Criteria criteria){
        Page page = null;
        try{
            criteria.setFirstResult((pageNum - 1) * pageSize);
            criteria.setMaxResults(pageSize);

            List result = criteria.list();

            // 获取根据条件分页查询的总行数
            int rowCount = getRowCount(criteria);

            page = new Page(pageNum, pageSize, rowCount, result);

        }catch (RuntimeException re){
            throw re;
        }finally{
            return page;
        }
    }

    private int getRowCount(Criteria criteria){
        //查总数要重设分页属性
        criteria.setFirstResult(0).setMaxResults(Integer.MAX_VALUE);
        return ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    /**
     * @param pageNum  当前页数
     * @param pageSize 每页显示的记录数
     * @param object   将查询条件封装为Object
     * @return 将查询结果封装为Page返回
     * @function 根据传递过来的Object, 分页显示在数据库中与其匹配的记录
     */
    public Page findPageByExample(int pageNum, int pageSize, Object object){
        Page page = null;
        try{
            Criteria criteria = this.getSession().createCriteria(object.getClass());

            if(object != null){
                criteria.add(Example.create(object).ignoreCase().enableLike(MatchMode.ANYWHERE));
            }

            // 获取根据条件分页查询的总行数
            int rowCount = getRowCount(criteria);

            criteria.setFirstResult((pageNum - 1) * pageSize);
            criteria.setMaxResults(pageSize);

            List result = criteria.list();

            page = new Page(pageNum, pageSize, rowCount, result);

        }catch (RuntimeException re){
            throw re;
        }finally{
            return page;
        }
    }

    /** like 查询,分页显示符合所有的记录数，将查询结果封装为Page
     * @param pageNum   当前页数
     * @param pageSize 每页显示的条数
     * @param map      将查询条件封装为map
     */
    public Page findPageByCriteriaLike(int pageNum, int pageSize,Class clazz, Map map){
        Page page = null;
        try{
            Criteria criteria = this.getSession().createCriteria(clazz);

            if(map != null){
                Set<String> keys = map.keySet();
                for(String key : keys){
                    criteria.add(Restrictions.like(key, map.get(key).toString(), MatchMode.ANYWHERE));
                }
            }

            // 获取根据条件分页查询的总行数
            int rowCount = getRowCount(criteria);

            criteria.setFirstResult((pageNum - 1) * pageSize);
            criteria.setMaxResults(pageSize);

            List result = criteria.list();

            page = new Page(pageNum, pageSize, rowCount, result);

        }catch (RuntimeException re){
            throw re;
        }finally{
            return page;
        }

    }

    /** 分页显示符合所有的记录数，将查询结果封装为Pager
     * @param pageNum     当前页数
     * @param pageSize   每页显示的条数
     * @param criterions 不定参数Criterion
     * @return 查询结果Page
     * @see
     * Criterion criterion1 = Restrictions.like("name", mendName, MatchMode.ANYWHERE)
     * Criterion criterion2 = Restrictions.eq("age", age)
     * findPageByCriterion({pageNum}, {pageSize}, Cat.class, criterion1, criterion2)
     */
    public Page findPageByCriterion(int pageNum, int pageSize, Class clazz, Criterion... criterions){
        Page page = null;
        try{
            Criteria criteria = this.getSession().createCriteria(clazz);
            for(Criterion criterion : criterions){
                if(criterion != null){
                    criteria.add(criterion);
                }
            }

            // 获取根据条件分页查询的总行数
            int rowCount = getRowCount(criteria);

            criteria.setFirstResult((pageNum - 1) * pageSize);
            criteria.setMaxResults(pageSize);

            List result = criteria.list();

            page = new Page(pageNum, pageSize, rowCount, result);

        }catch (RuntimeException re){
            throw re;
        }finally{
            return page;
        }

    }

    /** 分页显示符合所有的记录数，将查询结果封装为Page
     * @param pageNum   当前页数
     * @param pageSize 每页显示的条数
     * @param hql hql查询语句
     * @param map 用map封装查询条件,防止sql注入
     */
    public List<Object> findPageByHql(int pageNum, int pageSize, String hql, Map map){
        List<Object> result = null;
        try{
            Query query = this.getSession().createQuery(hql);

            Iterator it = map.keySet().iterator();
            while(it.hasNext()){
                Object key = it.next();
                query.setParameter(key.toString(), map.get(key));
            }

            query.setFirstResult((pageNum - 1) * pageSize);
            query.setMaxResults(pageSize);
            result = query.list();
        }catch (RuntimeException re){
            throw re;
        }
        return result;
    }

    /** 根据查询条件查询记录数的个数
     * @param hql hql查询语句
     * @param map 用map封装查询条件,防止sql注入
     * @return 数据库中满足查询条件的数据的条数
     */
    public int getTotalCountByHql(String hql, Map map){
        try{
            Query query = this.getSession().createQuery(hql);

            Iterator it = map.keySet().iterator();
            while(it.hasNext()){
                Object key = it.next();
                query.setParameter(key.toString(), map.get(key));
            }

            Integer i = (Integer) query.list().get(0);
            return i;
        }catch (RuntimeException re){
            throw re;
        }

    }
}
