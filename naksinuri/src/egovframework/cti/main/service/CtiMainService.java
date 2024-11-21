package egovframework.cti.main.service;

import java.util.List;

import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriZisikinVO;

public interface CtiMainService {
	public List<NaksinuriZisikinVO> get_cti_zazu_list(NaksinuriZisikinVO naksinuriZisikinVO) throws Exception;
	
	public int get_cti_zazu_list_totcnt(NaksinuriZisikinVO naksinuriZisikinVO) throws Exception;
	
	public List<CtiBoardVO> get_cti_manual_list(CtiBoardVO ctiBoardVO) throws Exception;
	
	public void set_cti_manual_insert(CtiBoardVO insertCtiBoardVO) throws Exception;

	public void set_cti_menual_mod(CtiBoardVO updateCtiBoardVO)throws Exception;

	public void set_cti_menual_delete(CtiBoardVO deleteCtiBoardVO)throws Exception;
}
