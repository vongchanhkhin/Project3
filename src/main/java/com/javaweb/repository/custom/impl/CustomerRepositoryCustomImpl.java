package com.javaweb.repository.custom.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import com.javaweb.utils.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CustomerRepositoryCustomImpl implements CustomerRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    private static Integer TOTAL_ITEM = 0;

    public static void joinTable(CustomerSearchBuilder customerSearchBuilder, StringBuilder sql) {
        Long staffId = customerSearchBuilder.getStaffId();

        if (staffId != null) {
            sql.append("JOIN assignmentcustomer ON c.id = assignmentcustomer.customerid ");
        }
    }

    public static void queryNormal(CustomerSearchBuilder customerSearchBuilder, StringBuilder where) {

        try {
            Field[] fields = CustomerSearchBuilder.class.getDeclaredFields();

            for (Field f : fields) {
                f.setAccessible(true);
                String fieldName = f.getName();

                if (!fieldName.equals("staffId")) {

                    Object value = f.get(customerSearchBuilder);

                    if (value != null) {
                        if (f.getType().getName().equals("java.lang.Integer")
                                || f.getType().getName().equals("java.lang.Long")
                                || f.getType().getName().equals("java.lang.Float")
                                || f.getType().getName().equals("java.lang.Double")) {

                            where.append("AND c." + fieldName.toLowerCase() + " = " + value + " ");

                        } else if (f.getType().getName().equals("java.lang.String") && StringUtils.check(value.toString())) {

                            where.append("AND c." + fieldName.toLowerCase() + " LIKE '%" + value + "%' ");

                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void querySpecial(CustomerSearchBuilder customerSearchBuilder, StringBuilder where) {
        Long staffId = customerSearchBuilder.getStaffId();
        if (staffId != null) {
            where.append("AND assignmentcustomer.staffid = " + staffId + " ");
        }
    }

    @Override
    public List<CustomerEntity> findAll(CustomerSearchBuilder customerSearchBuilder) {
        StringBuilder sql = new StringBuilder("SELECT c.* FROM customer c ");
        StringBuilder where = new StringBuilder("where 1 = 1 ");

        joinTable(customerSearchBuilder, sql);
        queryNormal(customerSearchBuilder, where);
        querySpecial(customerSearchBuilder, where);

        where.append("GROUP BY c.id;");
        sql.append(where);

        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
        TOTAL_ITEM = query.getResultList().size();

        return query.getResultList();
    }

    @Override
    public int countTotalItems() {
        return TOTAL_ITEM;
    }
}
