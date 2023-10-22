package com.evigel.sunnytable.service;

import com.evigel.sunnytable.dto.FileDto;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author evigel
 * @since 2021-04-28
 */
public interface IFileService {
    List<FileDto> openDir(String uid, long did);
    FileDto createDir(String userId,long dirId,String name);
    Long createRoot(String userId);
    void move(long srcFid, long dstFid);
    void remove(long srcFid);
    void rename(long srcFid, String srcName);
}
