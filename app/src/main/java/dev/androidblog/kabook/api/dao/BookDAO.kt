package dev.androidblog.kabook.api.dao

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookDAO(
    val meta: Meta,
    val documents: ArrayList<Documents>
) : Parcelable {

    @Parcelize
    data class Meta(
        val total_count: Int,
        val pageable_count: Int,
        val is_end: Boolean
    ) : Parcelable

    @Parcelize
    data class Documents(
        val title: String,
        val contents: String,
        val url: String,
        val isbn: String,
        val datetime: String,
        val authors: ArrayList<String>,
        val publisher: String,
        val translators: ArrayList<String>,
        val price: Int,
        val sale_price: Int,
        val thumbnail: String,
        val status: String
    ) : Parcelable
}
//total_count	Integer	검색된 문서 수
//pageable_count	Integer	중복된 문서를 제외하고, 처음부터 요청 페이지까지의 노출 가능 문서 수
//is_end	Boolean	현재 페이지가 마지막 페이지인지 여부, 값이 false면 page를 증가시켜 다음 페이지를 요청할 수 있음

//title	String	도서 제목
//contents	String	도서 소개
//url	String	도서 상세 URL
//isbn	String	국제 표준 도서번호, ISBN10 또는 ISBN13 //ISBN10, ISBN13 중 하나 이상 존재, 공백(' ')으로 구분
//datetime	Datetime	도서 출판날짜, ISO 8601 형식 //[YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
//authors	String[]	도서 저자 리스트
//publisher	String	도서 출판사
//translators	String[]	도서 번역자 리스트
//price	Integer	도서 정가
//sale_price	Integer	도서 판매가
//thumbnail	String	도서 표지 미리보기 URL
//status	String	도서 판매 상태 정보 (정상, 품절, 절판 등)
//상황에 따라 변동 가능성이 있으므로 문자열 처리 지양, 단순 노출 요소로 활용 권장