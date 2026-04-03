import request from '@/utils/request';
import { AxiosPromise } from 'axios';

/**
 * AI股票分析接口
 * @param symbol 股票代码
 * @returns AI分析结果
 */
export const aiChatModel = (symbol: string): AxiosPromise<string> => {
  return request({
    url: '/market/AiChatModel/' + symbol,
    method: 'get'
  });
};
