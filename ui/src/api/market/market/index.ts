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
 * 新增股票信息
 * @param data
 */
export const addStock = (data: StockUsaForm) => {
  return request({
    url: '/market',
    method: 'post',
    data: data
  });
};

/**
 * 修改股票信息
 * @param data
 */
export const updateStock = (data: StockUsaForm) => {
  return request({
    url: '/market',
    method: 'put',
    data: data
  });
};

/**
 * 删除股票信息
 * @param id
 */
export const delStock = (ids: string | number | Array<string | number>) => {
  return request({
    url: '/market/' + ids,
    method: 'delete'
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

/**
 * 根据股票代码查询所有股票信息
 * @param symbol 股票代码
 */
export const getStockBySymbol = (symbol: string): AxiosPromise<StockUsaVo[]> => {
  return request({
    url: '/market/symbol/' + symbol,
    method: 'get'
  });
};
