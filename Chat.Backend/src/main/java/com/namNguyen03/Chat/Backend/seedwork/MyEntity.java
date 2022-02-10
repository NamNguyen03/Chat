/**
 * 
 */
package com.namNguyen03.Chat.Backend.seedwork;

import java.util.UUID;

/**
 * @author nam
 *
 */
public interface MyEntity {
	void setId(int id);
    int getId();
    void setUuid(UUID uuid);
    UUID getUuid();
}
