import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { StockVO, StockForm, StockQuery } from '@/api/stock/stock/types';

/**
 * 查询股票信息列表
 * @param query
 * @returns {*}
 */

export const listStock = (query?: StockQuery): AxiosPromise<StockVO[]> => {
  return request({
    url: '/stock/stock/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询股票信息详细
 * @param id
 */
export const getStock = (id: string | number): AxiosPromise<StockVO> => {
  return request({
    url: '/stock/stock/' + id,
    method: 'get'
  });
};

/**
 * 新增股票信息
 * @param data
 */
export const addStock = (data: StockForm) => {
  return request({
    url: '/stock/stock',
    method: 'post',
    data: data
  });
};

/**
 * 修改股票信息
 * @param data
 */
export const updateStock = (data: StockForm) => {
  return request({
    url: '/stock/stock',
    method: 'put',
    data: data
  });
};

/**
 * 删除股票信息
 * @param id
 */
export const delStock = (id: string | number | Array<string | number>) => {
  return request({
    url: '/stock/stock/' + id,
    method: 'delete'
  });
};
