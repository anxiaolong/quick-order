package com.chengdu.qo.rest.service.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface PartnerKeyMapper {
    @Select("select t.`key` from qo_partner_key t where t.pid = #{partnerId}")
    String selKeyByPartnerId(@Param("partnerId") int partnerId);
}
