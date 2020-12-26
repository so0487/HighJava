package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.prod.vo.LProdVo;
import kr.or.ddit.prod.vo.ProdVo;

public interface IProdService {
	public List<LProdVo> getLprod();

	public List<ProdVo> getProdId(String prodId);

	public List<ProdVo> getProdLgu(String prodLgu);
}
