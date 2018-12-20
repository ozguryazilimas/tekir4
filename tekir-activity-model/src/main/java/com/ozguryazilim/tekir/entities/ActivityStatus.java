package com.ozguryazilim.tekir.entities;

/**
 * Activity'inin durum bilgisi
 *
 * @author Hakan Uygun
 * @author Hüseyin Ateş
 */
public enum ActivityStatus {
    /**
     * Yeni tanım yapılıyor. Henüz Open durumda değil
     */
    DRAFT,
    /**
     * Kestirilemiyor. Bir kaynaktan aktarılmış fakat tam ilişkilendirilememiş
     */
    UNRESOLVED,
    /**
     * Zamanlanmış durumda. Zamanı ( DUE_DATE ) gelince yapılacak
     */
    SCHEDULED,
    /**
     * Açık durumda bekliyor. Henüz zamanlanmadı
     */
    OPEN,
    /**
     * Başarı ile kapandı
     */
    SUCCESS,
    /**
     * Başarısız kapandı
     */
    FAILED
}
