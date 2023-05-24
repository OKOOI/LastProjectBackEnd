package project.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import project.dto.AccompanyDto;
import project.dto.NoticeDto;
import project.dto.QnaCommentDto;
import project.dto.QnaDto;
import project.service.ProjectService;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ApiController {

	final String UPLOAD_DIR = "C:/java/upload/";

	@Autowired
	ProjectService projectService;

	// 공지사항 리스트 조회
	@GetMapping("/api/noticeList")
	public ResponseEntity<List<NoticeDto>> listNotice() throws Exception {
		List<NoticeDto> NoticetDto = projectService.listNoticeDto();
		if (NoticetDto == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(NoticetDto);
		}
	}

	// 공지사항 글별 상세페이지 조회
	@GetMapping("/api/notice/{noticeIdx}")
	public ResponseEntity<NoticeDto> noticeDetail(@PathVariable("noticeIdx") int noticeIdx) throws Exception {
		NoticeDto noticeDto = projectService.noticeDetail(noticeIdx);
		if (noticeDto == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(noticeDto);
		}
	}

	// 공지사항 작성
	@PostMapping("/api/notice/write")
	public ResponseEntity<String> insertNotice(@RequestBody NoticeDto noticeDto) throws Exception {
		int insertedCount = projectService.insertNotice(noticeDto);
		if (insertedCount != 1) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("작성을 실패하였습니다");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body("작성을 성공하였습니다");
		}
	}

	// 공지사항 삭제
	@DeleteMapping("/api/notice/{noticeIdx}")
	public ResponseEntity<Integer> deleteNotice(@PathVariable("noticeIdx") int noticeIdx) throws Exception {
		int deletedCount = projectService.deleteNotice(noticeIdx);
		if (deletedCount != 1) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(deletedCount);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(deletedCount);
		}
	}

	// 공지사항 수정
	@PutMapping("/api/notice/update/{noticeIdx}")
	public ResponseEntity<Integer> updateNotice(@PathVariable("noticeIdx") int noticeIdx,
			@RequestBody NoticeDto noticeDto) throws Exception {
		noticeDto.setNoticeIdx(noticeIdx);
		int updatedCount = projectService.updateNotice(noticeDto);
		if (updatedCount != 1) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updatedCount);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(updatedCount);
		}
	}

	// QNA 리스트 조회
//	@GetMapping("/api/qnalist")
//	public ResponseEntity<List<QnaDto>> listQna() throws Exception {
//		List<QnaDto> QnaDto = projectService.listQnaDto();
//		if (QnaDto == null) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//		} else {
//			return ResponseEntity.status(HttpStatus.OK).body(QnaDto);
//		}
//	}

	// QNA 상세페이지 조회
	// @GetMapping("/api/qna/{qnaIdx}")
	// public ResponseEntity<QnaDto> qnaDetail(@PathVariable("qnaIdx") int qnaIdx)
	// throws Exception {
	// QnaDto qnaDto = projectService.qnaDetail(qnaIdx);
	// if (qnaDto == null) {
	// return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	// } else {
	// return ResponseEntity.status(HttpStatus.OK).body(qnaDto);
	// }
	// }

	// QNA 작성
	@PostMapping("/api/qna/write")
	public ResponseEntity<String> insertQna(@RequestBody QnaDto qnaDto) throws Exception {
		int insertedCount = projectService.insertQna(qnaDto);
		if (insertedCount != 1) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("작성을 실패하였습니다");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body("작성을 성공하였습니다");
		}
	}

	// QNA 삭제
	@DeleteMapping("/api/qna/{qnaIdx}")
	public ResponseEntity<Integer> deleteQna(@PathVariable("qnaIdx") int qnaIdx) throws Exception {
		int deletedCount = projectService.deleteQna(qnaIdx);
		if (deletedCount != 1) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(deletedCount);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(deletedCount);
		}
	}

	// QNA 수정
	@PutMapping("/api/qna/update/{qnaIdx}")
	public ResponseEntity<Integer> updateQna(@PathVariable("qnaIdx") int qnaIdx, @RequestBody QnaDto qnaDto)
			throws Exception {
		qnaDto.setQnaIdx(qnaIdx);
		int updatedCount = projectService.updateQna(qnaDto);
		if (updatedCount != 1) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updatedCount);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(updatedCount);
		}
	}

	// QNA 페이징 및 검색
	@GetMapping("/api/qnalistbypage")
	public ResponseEntity<List<QnaDto>> listQnaDtoByPages(@RequestParam("pages") int pages,
			@RequestParam("search") String search) throws Exception {

		String decodeSearch = URLDecoder.decode(search, "UTF-8");
		List<QnaDto> QnaDto = projectService.listQnaDtoByPages(pages, decodeSearch);

		if (QnaDto == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(QnaDto);
		}
	}

	// QNA 페이지수 조회
	@GetMapping("/api/qnapagecount")
	public ResponseEntity<Integer> listPageCount(@RequestParam("pages") int pages,
			@RequestParam("search") String search) throws Exception {
		String decodeSearch = URLDecoder.decode(search, "UTF-8");

		int pCount = projectService.listQnaDtoSearchPageCount(decodeSearch);

		return ResponseEntity.status(HttpStatus.OK).body(pCount);
	}

	// QNA 페이지수 조회
//	@GetMapping("/api/qnapagecounts")
//	public ResponseEntity<Integer> listQnaDtoPageCount(@RequestParam("pages") int pages) throws Exception {
//
//		
//		int pCount1 = projectService.listQnaDtoPageCount();
//
//		return ResponseEntity.status(HttpStatus.OK).body(pCount1);
//	}

	// QNA 상세페이지 COMMENTS 리스트
	@GetMapping("/api/qna/{qnaIdx}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> qnaDetail(@PathVariable("qnaIdx") int qnaIdx) throws Exception {
		List<QnaCommentDto> list = projectService.selectCommentList(qnaIdx);
		QnaDto qnaDto = projectService.selectQnaInfo(qnaIdx);

		Map<String, Object> result = new HashMap<>();

		result.put("selectCommentList", list);
		result.put("selectQnaInfo", qnaDto);
		if (result != null && result.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	// QNA COMMENTS 등록
	@PostMapping("/api/qna/comments/write/{qnaIdx}")
	public ResponseEntity<Map<String, Object>> insertcomments(@RequestBody QnaCommentDto qnaCommentDto,
			@PathVariable("qnaIdx") int qnaIdx) throws Exception {
		int insertedCount = 0;
		try {
			qnaCommentDto.setQnaIdx(qnaIdx);
			insertedCount = projectService.insertComment(qnaCommentDto);
			if (insertedCount > 0) {
				Map<String, Object> result = new HashMap<>();
				result.put("message", "정상적으로 등록되었습니다.");
				result.put("count", insertedCount);
				result.put("commentIdx", qnaCommentDto.getQnaCommentIdx());
				return ResponseEntity.status(HttpStatus.OK).body(result);
			} else {
				Map<String, Object> result = new HashMap<>();
				result.put("message", "등록된 내용이 없습니다.");
				result.put("count", insertedCount);
				return ResponseEntity.status(HttpStatus.OK).body(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> result = new HashMap<>();
			result.put("message", "등록 중 오류가 발생했습니다.");
			result.put("count", insertedCount);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
		}
	}

	// Accompany 페이징 및 검색
	@GetMapping("/api/accompanylistbypage")
	public ResponseEntity<List<AccompanyDto>> listAccompanyDtoByPages(@RequestParam("pages") int pages,
			@RequestParam("search") String search, @RequestParam("accompanyRegion") String accompanyRegion)
			throws Exception {

		String decodeSearch = URLDecoder.decode(search, "UTF-8");
		List<AccompanyDto> AccompanyDto = projectService.listAccompanyDtoByPages(pages, decodeSearch, accompanyRegion);

		if (AccompanyDto == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(AccompanyDto);
		}
	}

	// Accompany 페이지 수 조회
	@GetMapping("/api/accompanypagecount")
	public ResponseEntity<Integer> listAccompanyDtoPageCount(@RequestParam("pages") int pages,
			@RequestParam("search") String search, @RequestParam("accompanyRegion") String accompanyRegion)
			throws Exception {
		String decodeSearch = URLDecoder.decode(search, "UTF-8");
		String decodeRegion = URLDecoder.decode(accompanyRegion, "UTF-8");

		int pCount = projectService.listAccompanyDtoSearchPageCount(decodeSearch, decodeRegion);

		return ResponseEntity.status(HttpStatus.OK).body(pCount);
	}

	// Accompany 상세페이지 조회
	@GetMapping("/api/accompany/{accompanyIdx}")
	public ResponseEntity<AccompanyDto> accompanyDetail(@PathVariable("accompanyIdx") int accompanyIdx)
			throws Exception {
		AccompanyDto accompanyDto = projectService.accompanyDetail(accompanyIdx);
		if (accompanyDto == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(accompanyDto);
		}
	}

	// Accompany 상세페이지 이미지 조회
	@GetMapping("/api/getImage/{filename}")
	public void getImage(@PathVariable("filename") String filename, HttpServletResponse response) throws Exception {
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {
			response.setHeader("Content-Disposition", "inline;"); // 4

			byte[] buf = new byte[1024];
			fis = new FileInputStream(UPLOAD_DIR + filename);
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(response.getOutputStream());
			int read;
			while ((read = bis.read(buf, 0, 1024)) != -1) {
				bos.write(buf, 0, read);
			}
//			} finally {
//				bos.close();
//				bis.close();
//				fis.close();
//			}
		} finally {
			if (bos != null) {
				bos.close();
			}
			if (bis != null) {
				bis.close();
			}
			if (fis != null) {
				fis.close();
			}
		}
	}

	// Accompany 작성
	@PostMapping("/api/accompany/write")
	public ResponseEntity<String> insertAccompany(
			@RequestPart(value = "accompanyImg", required = false) MultipartFile[] accompanyImg,
			@RequestPart(value = "data", required = false) AccompanyDto data) throws Exception {

		List<Map<String, String>> resultList = saveFiles(accompanyImg);
		for (Map<String, String> result : resultList) {
			String accImg = result.get("savedFileName");
			data.setAccompanyImage(accImg);
		}

		projectService.insertAccompany(data);

		if (data != null) {
			return ResponseEntity.status(HttpStatus.OK).body("정상 처리");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("오류 발생");
		}
	}

	// 사진(다수) 저장 함수
	private List<Map<String, String>> saveFiles(MultipartFile[] files) {
		List<Map<String, String>> resultList = new ArrayList<>();

		if (files != null) {
			for (MultipartFile mf : files) {
				String originFileName = mf.getOriginalFilename();
				System.out.println(">>>>>>>>>>>>>" + originFileName);
				String savedFileName = System.currentTimeMillis() + originFileName;

				try {
					File f = new File(UPLOAD_DIR + savedFileName);
					mf.transferTo(f);

					Map<String, String> result = new HashMap<>();
					result.put("originalFileName", originFileName);
					result.put("savedFileName", savedFileName);
					resultList.add(result);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return resultList;
	}

	// 사진(단일) 저장 함수
//	private String saveFile(MultipartFile file) {
//		if (file != null) {
//			String originFileName = file.getOriginalFilename();
//			System.out.println(">>>>>>>>>>>>>" + originFileName);
//			String savedFileName = System.currentTimeMillis() + originFileName;
//
//			try {
//				File f = new File(UPLOAD_DIR + savedFileName);
//				file.transferTo(f);
//				return savedFileName;
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return null;
//	}

//	@PostMapping("/api/accompany/write")
//	public ResponseEntity<String> insertAccompany(@RequestBody AccompanyDto accompanyDto) throws Exception {
//		int insertedCount = projectService.insertAccompany(accompanyDto);
//		if (insertedCount != 1) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("작성을 실패하였습니다");
//		} else {
//			return ResponseEntity.status(HttpStatus.OK).body("작성을 성공하였습니다");
//		}
//	}

	// Accompany 삭제
	@DeleteMapping("/api/accompany/{accompanyIdx}")
	public ResponseEntity<Integer> deleteAccompany(@PathVariable("accompanyIdx") int accompanyIdx) throws Exception {
		int deletedCount = projectService.deleteAccompany(accompanyIdx);
		if (deletedCount != 1) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(deletedCount);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(deletedCount);
		}
	}

	// Accompany 수정
	@PutMapping("/api/accompany/update/{accompanyIdx}")
	public ResponseEntity<String> updateAccompany(@PathVariable("accompanyIdx") int accompanyIdx,
			@RequestPart(value = "accompanyImg", required = false) MultipartFile[] accompanyImg,
			@RequestPart(value = "data", required = false) AccompanyDto data) throws Exception {


		
		List<Map<String, String>> resultList = resaveFiles(accompanyImg);
		for (Map<String, String> result : resultList) {
			String accImg = result.get("savedFileName");
			data.setAccompanyImage(accImg);
		}

		projectService.updateAccompany(data);

		if (data != null) {
			return ResponseEntity.status(HttpStatus.OK).body("정상 처리");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("오류 발생");
		}
	}

	// 사진 재저장 함수
	private List<Map<String, String>> resaveFiles(MultipartFile[] files) {
		List<Map<String, String>> resultList = new ArrayList<>();

		if (files != null) {
			for (MultipartFile mf : files) {
				String originFileName = mf.getOriginalFilename();
				System.out.println(">>>>>>>>>>>>>" + originFileName);
				String savedFileName = System.currentTimeMillis() + originFileName;
				//
//						try {
//							File f = new File(UPLOAD_DIR + ); // 3 ??
//							mf.transferTo(f);

				try {
					File dir = new File(UPLOAD_DIR);
					if (!dir.exists()) {
						dir.mkdir();
					}
					File f = new File(dir, savedFileName);
					mf.transferTo(f);

					Map<String, String> result = new HashMap<>();
					result.put("originalFileName", originFileName);
					result.put("savedFileName", savedFileName);
					resultList.add(result);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return resultList;
	}

}
