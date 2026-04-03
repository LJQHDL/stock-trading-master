package io.github.linpeilie;

import org.dromara.common.log.event.OperLogEventToSysOperLogBoMapper;
import org.dromara.system.domain.SysClientToSysClientVoMapper;
import org.dromara.system.domain.SysConfigToSysConfigVoMapper;
import org.dromara.system.domain.SysDeptToSysDeptVoMapper;
import org.dromara.system.domain.SysDictDataToSysDictDataVoMapper;
import org.dromara.system.domain.SysDictTypeToSysDictTypeVoMapper;
import org.dromara.system.domain.SysLogininforToSysLogininforVoMapper;
import org.dromara.system.domain.SysMenuToSysMenuVoMapper;
import org.dromara.system.domain.SysNoticeToSysNoticeVoMapper;
import org.dromara.system.domain.SysOperLogToSysOperLogVoMapper;
import org.dromara.system.domain.SysOssConfigToSysOssConfigVoMapper;
import org.dromara.system.domain.SysOssToSysOssVoMapper;
import org.dromara.system.domain.SysPostToSysPostVoMapper;
import org.dromara.system.domain.SysRoleToSysRoleVoMapper;
import org.dromara.system.domain.SysSocialToSysSocialVoMapper;
import org.dromara.system.domain.SysTenantPackageToSysTenantPackageVoMapper;
import org.dromara.system.domain.SysTenantToSysTenantVoMapper;
import org.dromara.system.domain.SysUserToSysUserVoMapper;
import org.dromara.system.domain.bo.SysClientBoToSysClientMapper;
import org.dromara.system.domain.bo.SysConfigBoToSysConfigMapper;
import org.dromara.system.domain.bo.SysDeptBoToSysDeptMapper;
import org.dromara.system.domain.bo.SysDictDataBoToSysDictDataMapper;
import org.dromara.system.domain.bo.SysDictTypeBoToSysDictTypeMapper;
import org.dromara.system.domain.bo.SysLogininforBoToSysLogininforMapper;
import org.dromara.system.domain.bo.SysMenuBoToSysMenuMapper;
import org.dromara.system.domain.bo.SysNoticeBoToSysNoticeMapper;
import org.dromara.system.domain.bo.SysOperLogBoToOperLogEventMapper;
import org.dromara.system.domain.bo.SysOperLogBoToSysOperLogMapper;
import org.dromara.system.domain.bo.SysOssBoToSysOssMapper;
import org.dromara.system.domain.bo.SysOssConfigBoToSysOssConfigMapper;
import org.dromara.system.domain.bo.SysPostBoToSysPostMapper;
import org.dromara.system.domain.bo.SysRoleBoToSysRoleMapper;
import org.dromara.system.domain.bo.SysSocialBoToSysSocialMapper;
import org.dromara.system.domain.bo.SysTenantBoToSysTenantMapper;
import org.dromara.system.domain.bo.SysTenantPackageBoToSysTenantPackageMapper;
import org.dromara.system.domain.bo.SysUserBoToSysUserMapper;
import org.dromara.system.domain.vo.SysClientVoToSysClientMapper;
import org.dromara.system.domain.vo.SysConfigVoToSysConfigMapper;
import org.dromara.system.domain.vo.SysDeptVoToSysDeptMapper;
import org.dromara.system.domain.vo.SysDictDataVoToSysDictDataMapper;
import org.dromara.system.domain.vo.SysDictTypeVoToSysDictTypeMapper;
import org.dromara.system.domain.vo.SysLogininforVoToSysLogininforMapper;
import org.dromara.system.domain.vo.SysMenuVoToSysMenuMapper;
import org.dromara.system.domain.vo.SysNoticeVoToSysNoticeMapper;
import org.dromara.system.domain.vo.SysOperLogVoToSysOperLogMapper;
import org.dromara.system.domain.vo.SysOssConfigVoToSysOssConfigMapper;
import org.dromara.system.domain.vo.SysOssVoToSysOssMapper;
import org.dromara.system.domain.vo.SysPostVoToSysPostMapper;
import org.dromara.system.domain.vo.SysRoleVoToSysRoleMapper;
import org.dromara.system.domain.vo.SysSocialVoToSysSocialMapper;
import org.dromara.system.domain.vo.SysTenantPackageVoToSysTenantPackageMapper;
import org.dromara.system.domain.vo.SysTenantVoToSysTenantMapper;
import org.dromara.system.domain.vo.SysTenantVoToTenantListVoMapper;
import org.dromara.system.domain.vo.SysUserVoToSysUserMapper;
import org.dromara.web.domain.StockToStockVoMapper;
import org.dromara.web.domain.StockUsaToStockUsaVoMapper;
import org.dromara.web.domain.bo.StockBoToStockMapper;
import org.dromara.web.domain.bo.StockUsaBoToStockUsaMapper;
import org.dromara.web.domain.bo.StockUsaBoToStockUsaVoMapper;
import org.dromara.web.domain.vo.StockUsaVoToStockUsaMapper;
import org.dromara.web.domain.vo.StockVoToStockMapper;
import org.dromara.web.domain.vo.TenantListVoToSysTenantVoMapper;
import org.mapstruct.Builder;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(
    componentModel = "spring-lazy",
    uses = {ConverterMapperAdapter__434.class, SysSocialVoToSysSocialMapper.class, SysDictDataVoToSysDictDataMapper.class, StockVoToStockMapper.class, SysOssConfigToSysOssConfigVoMapper.class, SysLogininforVoToSysLogininforMapper.class, SysConfigBoToSysConfigMapper.class, SysTenantPackageToSysTenantPackageVoMapper.class, SysPostBoToSysPostMapper.class, SysOssConfigVoToSysOssConfigMapper.class, SysUserToSysUserVoMapper.class, SysDictTypeVoToSysDictTypeMapper.class, SysDictDataToSysDictDataVoMapper.class, SysMenuBoToSysMenuMapper.class, SysRoleVoToSysRoleMapper.class, SysTenantVoToTenantListVoMapper.class, StockUsaBoToStockUsaMapper.class, SysConfigToSysConfigVoMapper.class, SysOperLogBoToOperLogEventMapper.class, SysClientVoToSysClientMapper.class, SysOssConfigBoToSysOssConfigMapper.class, SysNoticeVoToSysNoticeMapper.class, SysDeptBoToSysDeptMapper.class, StockUsaBoToStockUsaVoMapper.class, SysOssBoToSysOssMapper.class, SysMenuVoToSysMenuMapper.class, SysMenuToSysMenuVoMapper.class, SysTenantVoToSysTenantMapper.class, SysUserBoToSysUserMapper.class, StockUsaVoToStockUsaMapper.class, SysClientBoToSysClientMapper.class, SysDictDataBoToSysDictDataMapper.class, SysUserVoToSysUserMapper.class, SysClientToSysClientVoMapper.class, SysNoticeToSysNoticeVoMapper.class, SysOssToSysOssVoMapper.class, SysOperLogToSysOperLogVoMapper.class, SysLogininforBoToSysLogininforMapper.class, SysDeptVoToSysDeptMapper.class, SysTenantToSysTenantVoMapper.class, SysConfigVoToSysConfigMapper.class, StockBoToStockMapper.class, StockToStockVoMapper.class, SysSocialBoToSysSocialMapper.class, SysRoleBoToSysRoleMapper.class, SysOssVoToSysOssMapper.class, SysDictTypeToSysDictTypeVoMapper.class, SysRoleToSysRoleVoMapper.class, StockUsaToStockUsaVoMapper.class, SysTenantPackageVoToSysTenantPackageMapper.class, SysTenantBoToSysTenantMapper.class, SysTenantPackageBoToSysTenantPackageMapper.class, SysLogininforToSysLogininforVoMapper.class, TenantListVoToSysTenantVoMapper.class, SysPostVoToSysPostMapper.class, SysSocialToSysSocialVoMapper.class, SysDictTypeBoToSysDictTypeMapper.class, OperLogEventToSysOperLogBoMapper.class, SysOperLogBoToSysOperLogMapper.class, SysOperLogVoToSysOperLogMapper.class, SysDeptToSysDeptVoMapper.class, SysNoticeBoToSysNoticeMapper.class, SysPostToSysPostVoMapper.class},
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    builder = @Builder(buildMethod = "build", disableBuilder = true)
)
public interface AutoMapperConfig__434 {
}
