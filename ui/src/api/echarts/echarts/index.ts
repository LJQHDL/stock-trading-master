import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { StockUsaVo, StockUsaForm, StockUsaQuery } from './types';
import { StockVO } from '@/api/stock/stock/types';
/**
 * 查询股票信息列表
 * @param query
 * @returns {*}
 */

export const listStock = (query?: StockUsaQuery): AxiosPromise<StockUsaVo[]> => {
  return request({
    url: '/market/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询股票信息详细
 * @param id
 */
export const getStock = (id: string | number): AxiosPromise<StockUsaVo> => {
  return request({
    url: '/market/' + id, // 添加了斜杠
    method: 'get'
  });
};

/**
 *
 *查询股票Symbol代码信息
 *
 **/
export const getStockSymbols = () => {
  return request({
    url: '/market/symbols',
    method: 'get'
  });
};
