package egovframework.naksinuri_original.let.naksinuri.service;

import java.util.List;

public interface NaksinuriGoneService {
	
	public List<NaksinuriGoneVO> getListGone(NaksinuriGoneVO GoneVO) throws Exception;
	public List<NaksinuriGoneVO> getListGonemobile(NaksinuriGoneVO GoneVO) throws Exception;
	public List<NaksinuriGoneVO> getListGone_admin(NaksinuriGoneVO GoneVO) throws Exception;
	public void insert_data(NaksinuriGoneVO GoneVO) throws Exception;
	public NaksinuriGoneVO promotion_findCorp(NaksinuriGoneVO GoneVO) throws Exception;
	public void update_data(NaksinuriGoneVO GoneVO) throws Exception;
	public void delete_list(NaksinuriGoneVO GoneVO) throws Exception;
	public List<NaksinuriGoneVO> getTrashGone_admin(NaksinuriGoneVO goneVO) throws Exception;
	public void trash_list(NaksinuriGoneVO goneVO) throws Exception;
	public void restore_plocation(NaksinuriGoneVO goneVO) throws Exception; 
}
