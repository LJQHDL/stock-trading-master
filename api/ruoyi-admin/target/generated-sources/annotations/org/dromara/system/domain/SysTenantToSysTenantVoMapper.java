package org.dromara.system.domain;

import io.github.linpeilie.AutoMapperConfig__434;
import io.github.linpeilie.BaseMapper;
import org.dromara.system.domain.bo.SysTenantBoToSysTenantMapper;
import org.dromara.system.domain.vo.SysTenantVo;
import org.dromara.system.domain.vo.SysTenantVoToSysTenantMapper;
import org.dromara.system.domain.vo.SysTenantVoToTenantListVoMapper;
import org.dromara.web.domain.vo.TenantListVoToSysTenantVoMapper;
import org.mapstruct.Mapper;

@Mapper(
    config = AutoMapperConfig__434.class,
    uses = {TenantListVoToSysTenantVoMapper.class,SysTenantVoToTenantListVoMapper.class,SysTenantBoToSysTenantMapper.class,SysTenantVoToSysTenantMapper.class},
    imports = {}
)
public interface SysTenantToSysTenantVoMapper extends BaseMapper<SysTenant, SysTenantVo> {
}
