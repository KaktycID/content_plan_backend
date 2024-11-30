package content.plan.users.service;

import content.plan.board.dto.DictDTO;
import content.plan.users.repository.IconsRepository;
import content.plan.users.structure.Icons;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static content.plan.board.mapper.Mapper.mapIcons;

@RequiredArgsConstructor
@Slf4j
@Service
public class IconServiceImpl implements IconService{

    private final IconsRepository repository;

    @Override
    public DictDTO getById(int id) {
       Icons icon = repository.findById(id).orElseThrow();
       return mapIcons(icon);
    }
}
