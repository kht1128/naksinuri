/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.eduadm.member.service;

import java.util.List;

public interface EduMemberService {
	EduMemberVO get_edu_member_info(EduMemberVO eduMemberVO);//회원정보 가져오기
	List<EduMemberVO> get_edu_member_list(EduMemberVO eduMemberVO);//회원 목록 리스트
	int get_edu_member_list_totcnt(EduMemberVO eduMemberVO);//회원 목록 총인원
	void remove_edu_member(EduMemberVO eduMemberVO);//회원 삭제 2단계 완전제거
	void del_edu_member(EduMemberVO eduMemberVO);//회원 삭제 1단계 정보수정
	void set_edu_member_mod(EduMemberVO eduMemberVO);//회원 수정
	String set_edu_member_reg(EduMemberVO eduMemberVO) throws Exception;//회원 등록
	int get_id_search(EduMemberVO eduMemberVO) throws Exception;//아이디중복체크	
	
	EduMemberVO get_edu_member_dtl_info(EduMemberVO eduMemberVO) throws Exception;//회원상세정보 가져오기
	void remove_edu_member_dtl(EduMemberVO eduMemberVO);//회원추가정보 삭제 2단계 완전제거
	void set_edu_member_dtl_mod(EduMemberVO eduMemberVO);//회원추가정보 수정
	String set_edu_member_dtl_reg(EduMemberVO eduMemberVO) throws Exception;//회원추가정보 등록
	int get_is_member_dtl(EduMemberVO eduMemberVO) throws Exception;//회원추가정보 존재 유무
	
	
	List<EduMemberVO> get_edu_member_dtl_list(EduMemberVO eduMemberVO) throws Exception;//회원추가상세정보 목록
	List<EduMemberVO> get_edu_member_dtl_all_list(EduMemberVO eduMemberVO) throws Exception;//회원추가상세정보 목록
	
	List<EduMemberVO> get_edu_member_target_list(EduMemberVO eduMemberVO) throws Exception;//교육대상자 조회
	int get_edu_member_target_list_totcnt(EduMemberVO eduMemberVO) throws Exception;//교육대상자 조회(카운트)
	
	List<EduMemberVO> get_edu_member_target_list_only(EduMemberVO eduMemberVO) throws Exception;	//교육대상자 조회 목록에서만 씀
	int get_edu_member_target_list_totcnt_only(EduMemberVO eduMemberVO) throws Exception;			//교육대상자 조회(카운트) 목록에서만 씀

	List<EduMemberVO> get_edu_member_target_add_list(EduMemberVO mEduMemberVO) throws Exception;//추가 가능한 교육대상자 조회(카운트)
	int get_edu_member_target_add_list_totcnt(EduMemberVO mEduMemberVO)throws Exception;//추가 가능한 교육대상자 조회(카운트)

	String set_edu_member_target_reg(EduMemberVO chkEduMemberVO) throws Exception;//해당 년도 교육대상자 추가
	void remove_edu_member_target(EduMemberVO eduMemberVO) throws Exception;//해당 년도 교육대상자 제거
	void set_edu_member_target_mod(EduMemberVO eduMemberVO) throws Exception;//해당 년도 교육대상자 정보에 교육과정 업데이트
	List<EduMemberVO> get_edu_member_target_all_list(EduMemberVO eduMemberVO) throws Exception;//모든 년도 교육대상자 목록

	List<EduMemberVO> get_edu_member_check_overlap_list(EduMemberVO eduMemberVO) throws Exception;//중복회원(검증) 리스트(이름,생년월일,연락처 등)
	List<EduMemberVO> get_edu_member_check_list(EduMemberVO eduMemberVO) throws Exception;//중복회원(검증) 리스트(이름,생년월일,연락처 등)

	List<EduMemberVO> get_edu_member_master_list(EduMemberVO eduMemberVO) throws Exception;//관리자 회원 목록 리스트
	int get_edu_member_master_list_totcnt(EduMemberVO eduMemberVO) throws Exception;//관리자 회원 목록 리스트
	void set_edu_member_master_auth_mod(EduMemberVO eduMemberVO) throws Exception;//회원권한수정
	
	void set_edu_member_trnsfer(EduMemberVO eduMemberVO) throws Exception;//회원관리주체변경

	void set_edu_member_memo_mod(EduMemberVO eduMemberVO) throws Exception;//회원메모변경
	EduMemberVO get_edu_member_master_info(EduMemberVO eduMemberVO) throws Exception;//관리자 회원 정보
	
	List<LogAdmAuthVO> get_edu_member_author_log_list(LogAdmAuthVO logAdmAuthVO) throws Exception;//관리자권한기록 리스트
	int get_edu_member_author_log_list_totcnt(LogAdmAuthVO logAdmAuthVO) throws Exception;//관리자권한기록 카운트
	void set_edu_member_author_log(LogAdmAuthVO logAdmAuthVO) throws Exception;//관리자권한기록 생성
	
	void set_edu_excel_upload_reg(EduExcelUploadVO eduExcelUploadVO) throws Exception;//엑셀 업로드 파일명 등록
	void set_edu_excel_upload_dtl_reg(EduExcelUploadVO eduExcelUploadVO) throws Exception;//엑셀 업로드 파일 데이터 등록
	List<EduExcelUploadVO> get_edu_excel_upload_list(EduExcelUploadVO eduExcelUploadVO) throws Exception;//엑셀 업로드 리스트
	int get_edu_excel_upload_list_totcnt(EduExcelUploadVO eduExcelUploadVO) throws Exception;//엑셀 업로드 리스트(카운트)
	List<EduExcelUploadVO> get_edu_excel_upload_dtl_list(EduExcelUploadVO eduExcelUploadVO) throws Exception;//엑셀 업로드 상세 리스트
	List<EduMemberVO> get_edu_member_dtl_excel_compare(EduMemberVO eduMemberVO) throws Exception;//회원상세 엑셀 업로드 비교하기 위한 리스트
	void remove_edu_excel_upload_list(EduExcelUploadVO eduExcelUploadVO) throws Exception;//엑셀 업로드 리스트 삭제
	void remove_edu_excel_upload_dtl_list(EduExcelUploadVO eduExcelUploadVO) throws Exception;//엑셀 업로드 상세 리스트 삭제
	EduExcelUploadVO get_edu_excel_upload_info(EduExcelUploadVO eduExcelUploadVO) throws Exception;//엑셀 업로드 가져오기
	
	void set_edu_excel_upload_request(EduExcelUploadVO eduExcelUploadVO) throws Exception;//엑셀 업로드 승인신청
	void set_edu_excel_upload_dtl_mod(EduExcelUploadVO eduExcelUploadVO) throws Exception;//엑셀 업로드 상세 수정
	void set_edu_excel_upload_confm(EduExcelUploadVO eduExcelUploadVO) throws Exception;//엑셀 업로드 승인하기
	
	void remove_edu_member_all_target(EduMemberVO delEduMemberVO)throws Exception;//회원 교육대상자정보 일괄처리;
	
	void set_change_pwd(EduMemberVO eduMemberVO)throws Exception;
	
	void set_edu_excel_upload_del(EduExcelUploadVO excelUploadVO) throws Exception;  //엑셀업로드파일 삭제
	void set_edu_excel_upload_dtl_del(EduExcelUploadVO excelUploadVO) throws Exception; //엑셀업로드파일 삭제
	List<EduMemberVO> get_api_edu_member_list(EduMemberVO eduMemberInfo);
	int get_api_edu_member_list_totcnt(EduMemberVO eduMemberInfo);
	
	void set_dpcn_member_dtl_mod(EduMemberVO eduMemberVO) throws Exception;
	void set_dpcn_member_edu_trgt_mod(EduMemberVO eduMemberVO) throws Exception;
	void set_dpcn_member_edu_hstry_mod(EduMemberVO eduMemberVO) throws Exception;
	void set_dpcn_member_edu_hstry_dtl_mod(EduMemberVO eduMemberVO) throws Exception;
	void set_dpcn_member_edu_crtf_mod(EduMemberVO eduMemberVO) throws Exception;
	void set_dpcn_member_edu_crtf_dtl_mod(EduMemberVO eduMemberVO) throws Exception;
	void set_dpcn_member_survey_answer_mod(EduMemberVO eduMemberVO) throws Exception;
	void remove_edu_member_hstry(EduMemberVO eduMemberVO) throws Exception;
	void remove_edu_member_hstry_dtl(EduMemberVO eduMemberVO) throws Exception;
	void remove_edu_crtf(EduMemberVO eduMemberVO) throws Exception;
	void remove_edu_crtf_dtl(EduMemberVO eduMemberVO) throws Exception;
}
