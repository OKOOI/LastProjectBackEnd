package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.dto.AccompanyDto;
import project.dto.NoticeDto;
import project.dto.QnaCommentDto;
import project.dto.QnaDto;
import project.dto.TriedDto;
import project.mapper.ProjectMapper;

@Service
public class ProjectService {

	@Autowired
	private ProjectMapper mapper;

	// 공지사항 리스트 조회
	public List<NoticeDto> listNoticeDto() throws Exception {
		return mapper.listNoticeDto();
	}

	// 공지사항 글별 상세페이지 조회
	public NoticeDto noticeDetail(int noticeIdx) throws Exception {

		return mapper.noticeDetail(noticeIdx);
	}

	// 공지사항 작성
	public int insertNotice(NoticeDto noticeDto) throws Exception {
		return mapper.insertNotice(noticeDto);
	}

	// 공지사항 삭제
	public int deleteNotice(int noticeIdx) throws Exception {
		return mapper.deleteNotice(noticeIdx);
	}

	// 공지사항 수정
	public int updateNotice(NoticeDto noticeDto) throws Exception {
		return mapper.updateNotice(noticeDto);
	}

	// QNA 리스트 조회
//	public List<QnaDto> listQnaDto() throws Exception {
//		return mapper.listQnaDto();
//	}

	// QNA 상세페이지 조회
	// public QnaDto qnaDetail(int qnaIdx) throws Exception {
	// return mapper.qnaDetail(qnaIdx);
	// }

	// QNA 작성
	public int insertQna(QnaDto qnaDto) throws Exception {
		return mapper.insertQna(qnaDto);
	}

	// QNA 삭제
	public int deleteQna(int qnaIdx) throws Exception {
		return mapper.deleteQna(qnaIdx);
	}

	// QNA 수정
	public int updateQna(QnaDto qnaDto) throws Exception {
		return mapper.updateQna(qnaDto);
	}

	// QNA 페이지별 조회
	public List<QnaDto> listQnaDtoByPages(int pages, String search) throws Exception {

		int offsetStart = (pages - 1) * 10;

		return mapper.listQnaDtoByPages(offsetStart, search);
	}

	// QNA 페이지수 조회
	public int listQnaDtoSearchPageCount(String search) throws Exception {
		return mapper.listQnaDtoSearchPageCount(search);
	}
//	public int listQnaDtoPageCount()throws Exception {
//listTriedDtoByPages
//		return mapper.listQnaDtoPageCount();
//	}

	// QNA 상세페이지 COMMENT 리스트
	public List<QnaCommentDto> selectCommentList(int qnaIdx) throws Exception {
		return mapper.selectCommentList(qnaIdx);
	}

	// QNA 상세페이지 조회
	public QnaDto selectQnaInfo(int qnaIdx) throws Exception {
		return mapper.selectQnaInfo(qnaIdx);
	}

	// QNA 상세페이지 COMMENT 등록
	public int insertComment(QnaCommentDto qnaCommentDto) throws Exception {
		return mapper.insertComment(qnaCommentDto);
	}

	// Accompany 페이징 및 검색
	public List<AccompanyDto> listAccompanyDtoByPages(int pages, String search, String accompanyRegion) throws Exception {

		int offsetStart = (pages - 1) * 9;

		return mapper.listAccompanyDtoByPages(offsetStart, search, accompanyRegion);
	}

	// Accompany 페이지 수 조회
	public int listAccompanyDtoSearchPageCount(String search) throws Exception {
		return mapper.listAccompanyDtoSearchPageCount(search);
	}

	// Accompany  상세페이지 조회 및 조회수 증가
	public AccompanyDto accompanyDetail(int accompanyIdx) throws Exception {
		mapper.updateCount(accompanyIdx);
		return mapper.accompanyDetail(accompanyIdx);
	}

	// Accompany 작성
	public int insertAccompany(AccompanyDto accompanyDto) throws Exception {
		return mapper.insertAccompany(accompanyDto);
	}

	// Accompany 삭제
	public int deleteAccompany(int accompanyIdx) throws Exception {
		return mapper.deleteAccompany(accompanyIdx);
	}

	// Accompany 수정
	public int updateAccompany(AccompanyDto accompanyDto) throws Exception {
		return mapper.updateAccompany(accompanyDto);
	}

}
