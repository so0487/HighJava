package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.prod.dao.IprodDao;
import kr.or.ddit.prod.vo.LProdVo;
import kr.or.ddit.prod.vo.ProdVo;

public class ProdServiceImpl implements IProdService{
	private static ProdServiceImpl service;
	private IprodDao dao;


	@Override
	public List<LProdVo> getLprod() {
		return dao.getLprod();
	}
	@Override
	public List<ProdVo> getProdId(String prodId) {
		return dao.getProdId(prodId);
	}
	@Override
	public List<ProdVo> getProdLgu(String prodLgu) {
		return dao.getProdLgu(prodLgu);
	}
}
