package com.evigel.sunnytable.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.springframework.util.StringUtils;

public class UpdateBatchInjector extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        final String sql = "<script>insert into %s %s values %s ON DUPLICATE KEY UPDATE %s</script>";
        final String tableName = tableInfo.getTableName();
        final String filedSql = prepareFieldSql(tableInfo);
        final String modelValuesSql = prepareModelValuesSql(tableInfo);
        final String duplicateKeySql =prepareDuplicateKeySql(tableInfo);
        final String sqlResult = String.format(sql, tableName, filedSql, modelValuesSql,duplicateKeySql);
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sqlResult, modelClass);
        return this.addInsertMappedStatement(mapperClass, modelClass, "updateBatch", sqlSource, new NoKeyGenerator(), null, null);
    }


    private String prepareFieldSql(TableInfo tableInfo) {
        StringBuilder fieldSql = new StringBuilder();
        //fieldSql.append(tableInfo.getKeyColumn()).append(",");
//        tableInfo.getFieldList().forEach(x -> fieldSql.append(x.getColumn()).append(","));
//        fieldSql.delete(fieldSql.length() - 1, fieldSql.length());
        fieldSql.append(tableInfo.getAllSqlSelect());
        fieldSql.insert(0, "(");
        fieldSql.append(")");
        return fieldSql.toString();

    }

//    private String prepareValuesSql(TableInfo tableInfo) {
//        final StringBuilder valueSql = new StringBuilder();
//        valueSql.append("<foreach collection=\"list\" item=\"item\" index=\"index\" open=\"(\" separator=\"),(\" close=\")\">");
//        valueSql.append("#{item.").append(tableInfo.getKeyProperty()).append("},");
//        tableInfo.getFieldList().forEach(x -> valueSql.append("#{item.").append(x.getProperty()).append("},"));
//        valueSql.delete(valueSql.length() - 1, valueSql.length());
//        valueSql.append("</foreach>");
//        return valueSql.toString();
//
//    }


    /**
     * 准备属性名
     * @param tableInfo
     * @return
     */
    private String prepareDuplicateKeySql(TableInfo tableInfo) {
        final StringBuilder duplicateKeySql = new StringBuilder();
        if(!StringUtils.isEmpty(tableInfo.getKeyColumn())) {
            duplicateKeySql.append(tableInfo.getKeyColumn()).append("=(").append(tableInfo.getKeyColumn()).append("),");
        }

        tableInfo.getFieldList().forEach(x -> {
            duplicateKeySql.append(x.getColumn())
                    .append("=VALUES(")
                    .append(x.getColumn())
                    .append("),");
        });
        duplicateKeySql.delete(duplicateKeySql.length() - 1, duplicateKeySql.length());
        return duplicateKeySql.toString();
    }

    private String prepareModelValuesSql(TableInfo tableInfo){
        final StringBuilder valueSql = new StringBuilder();
        valueSql.append("<foreach collection=\"list\" item=\"item\" index=\"index\" open=\"(\" separator=\"),(\" close=\")\">");
        if(!StringUtils.isEmpty(tableInfo.getKeyProperty())) {
            valueSql.append("#{item.").append(tableInfo.getKeyProperty()).append("},");
        }
        tableInfo.getFieldList().forEach(x -> valueSql.append("#{item.").append(x.getProperty()).append("},"));
        valueSql.delete(valueSql.length() - 1, valueSql.length());
        valueSql.append("</foreach>");
        return valueSql.toString();
    }
}
