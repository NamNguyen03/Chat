/**
 * 
 */
package com.namNguyen03.Chat.Backend.seedwork;

import java.time.LocalDate;

/**
 * @author nam
 *
 */
public interface MyAuditable {
    String getCreateBy();
    String getUpdateBy();
    LocalDate getCreateDate();
    LocalDate getUpdateDate();
    void setCreateBy(String createBy);
    void setUpdateBy(String updateBy);
    void setUpdateDate(LocalDate updateDate);
    void setCreateDate(LocalDate createDate);
}
