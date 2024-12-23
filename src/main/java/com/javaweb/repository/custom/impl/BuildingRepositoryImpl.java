package com.javaweb.repository.custom.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.utils.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    private static Integer TOTAL_ITEM = 0;

    public static void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
        Long staffId = buildingSearchBuilder.getStaffId();

        if (staffId != null) {
            sql.append("JOIN assignmentbuilding ON b.id = assignmentbuilding.buildingid ");
        }

//        List<String> typeCode = buildingSearchBuilder.getTypeCode();
//        if (typeCode != null && typeCode.size() != 0) {
//            sql.append("JOIN buildingrenttype ON b.id = buildingrenttype.buildingid ");
//            sql.append("JOIN renttype ON renttype.id = buildingrenttype.renttypeid ");
//        }
    }

    public static void queryNormal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {

        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();

            for (Field f : fields) {
                f.setAccessible(true);
                String fieldName = f.getName();

                if (!fieldName.equals("staffId")
                        && !fieldName.equals("typeCode")
                        && !fieldName.startsWith("area")
                        && !fieldName.startsWith("rentPrice")) {

                    Object value = f.get(buildingSearchBuilder);

                    if (value != null) {
                        if (f.getType().getName().equals("java.lang.Integer")
                                || f.getType().getName().equals("java.lang.Long")
                                || f.getType().getName().equals("java.lang.Float")
                                || f.getType().getName().equals("java.lang.Double")) {

                            where.append("AND b." + fieldName.toLowerCase() + " = " + value + " ");

                        } else if (f.getType().getName().equals("java.lang.String") && StringUtils.check(value.toString())) {

                            where.append("AND b." + fieldName.toLowerCase() + " LIKE '%" + value + "%' ");

                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
        Long staffId = buildingSearchBuilder.getStaffId();
        if (staffId != null) {
            where.append("AND assignmentbuilding.staffid = " + staffId + " ");
        }

        Long rentAreaFrom = buildingSearchBuilder.getAreaFrom();
        Long rentAreaTo = buildingSearchBuilder.getAreaTo();
        if (rentAreaFrom != null || rentAreaTo != null) {
            where.append("AND EXISTS (SELECT * FROM rentarea WHERE rentarea.buildingid = b.id ");

            if (rentAreaFrom != null) {
                where.append("AND rentarea.value >= " + rentAreaFrom + " ");
            }

            if (rentAreaTo != null) {
                where.append("AND rentarea.value <= " + rentAreaTo);
            }

            where.append(") ");
        }

        Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
        Long rentPriceTo = buildingSearchBuilder.getRentPriceTo();
        if (rentPriceFrom != null || rentPriceTo != null) {
            if (rentPriceFrom != null) {
                where.append("AND b.rentprice >= " + rentPriceFrom + " ");
            }

            if (rentPriceTo != null) {
                where.append("AND b.rentprice <= " + rentPriceTo + " ");
            }
        }

        // java 7
//		if (typeCodes != null && typeCodes.size() != 0) {
//			List<String> temp = new ArrayList<>();
//
//			for (String s : typeCodes) {
//				temp.add("'" + s + "'");
//			}
//
//			where.append("AND renttype.code IN (" + String.join(",", temp) + ") ");
//		}

        // java 8
        List<String> typeCode = buildingSearchBuilder.getTypeCode();
        if (typeCode != null && typeCode.size() != 0) {
            where.append("AND (");
            String str = typeCode.stream().map(i -> "b.type LIKE '%" + i + "%'")
                    .collect(Collectors.joining(" OR "));
            where.append(str + ") ");
        }
    }

    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sql = new StringBuilder("SELECT b.* FROM building b ");
        StringBuilder where = new StringBuilder("where 1 = 1 ");

        joinTable(buildingSearchBuilder, sql);
        queryNormal(buildingSearchBuilder, where);
        querySpecial(buildingSearchBuilder, where);

        where.append("GROUP BY b.id;");

        sql.append(where);

//        StringBuilder sqlCopy = new StringBuilder(sql.toString());

//        Query query1 = entityManager.createNativeQuery(sqlCopy.toString(), BuildingEntity.class);
//        TOTAL_ITEM = query1.getResultList().size();

//        sql.append(" LIMIT ").append(pageable.getPageSize()).append("\n")
//                .append(" OFFSET ").append(pageable.getOffset());

        Query query2 = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        TOTAL_ITEM = query2.getResultList().size();

        return query2.getResultList();
    }

    @Override
    public int countTotalItems() {
        return TOTAL_ITEM;
    }
}
