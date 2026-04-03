package org.dromara.system.domain.vo;

import io.github.linpeilie.AutoMapperConfig__434;
import io.github.linpeilie.BaseMapper;
import org.dromara.system.domain.SysTenant;
import org.dromara.system.domain.SysTenantToSysTenantVoMapper;
import org.dromara.web.domain.vo.TenantListVoToSysTenantVoMapper;
import org.mapstruct.Mapper;

@Mapper(
    config = AutoMapperConfig__434.class,
    uses = {TenantListVoToSysTenantVoMapper.class,SysTenantVoToTenantListVoMapper.class,SysTenantToSysTenantVoMapper.class},
    imports = {}
)
public interface SysTenantVoToSysTenantMapper extends BaseMapper<SysTenantVo, SysTenant> {
}
