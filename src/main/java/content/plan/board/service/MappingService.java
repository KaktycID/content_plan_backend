package content.plan.board.service;

import content.plan.board.dto.DictDTO;
import content.plan.board.mapper.MapperDTO;

import java.util.List;

public interface MappingService {


    List<MapperDTO> getByEntityOne(Long entityId);
    List<MapperDTO> getByEntityTwo(Long entityId);
    MapperDTO create(MapperDTO mapperDTO);
    MapperDTO update(Long id, MapperDTO mapperDTO);

}
