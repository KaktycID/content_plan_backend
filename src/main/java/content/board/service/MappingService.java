package content.board.service;

import content.board.dto.MapperDTO;

import java.util.List;

public interface MappingService {


    List<MapperDTO> getByEntityOne(Long entityId);
    List<MapperDTO> getByEntityTwo(Long entityId);
    MapperDTO create(MapperDTO mapperDTO);
    MapperDTO delete(Long id, Long entityId);

}
