package content.plan.board.service;

import content.plan.board.dto.permission.BoardPermissionDTO;
import content.plan.board.mapper.DictionaryMapper;
import content.plan.board.repository.BoardPermissionRepository;
import content.plan.board.structure.BoardPermission;
import content.plan.users.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@RequiredArgsConstructor
@Slf4j
@Service
public class BoardPermissionServiceImp implements BoardPermissionService{

    private final BoardPermissionRepository repository;
    private static BoardServiceImpl boardService;
    private static DictionaryMapper dictMapper;
    private static UserServiceImpl userMapper;

    @Override
    public BoardPermissionDTO getByUserIdAndBoardId(Long userId, Long boardId) {
        return repository.findAll()
                .stream()
                .filter(i -> i.getId().equals(userId) && i.isActive() && i.getBoardId().equals(boardId))
                .map(BoardPermissionServiceImp::mapToDto)
                .findAny()
                .get();
    }

    @Override
    public BoardPermissionDTO create(BoardPermissionDTO boardPermissionDTO) {
        return null;
    }

    @Override
    public BoardPermissionDTO delete(BoardPermissionDTO boardPermissionDTO) {
        return null;
    }


    public static BoardPermissionDTO mapToDto(BoardPermission boardPermission) {

        return BoardPermissionDTO.builder()
                .id(boardPermission.getId())
                .board(boardService.mapToDto(boardPermission.getBoardId()))
                .permissionType(dictMapper.mapPermissionTypes(boardPermission.getPermissionType()))
                .user(userMapper.mapToDto(boardPermission.getUserId()))
                .createDate(boardPermission.getCreateDate().toInstant().toEpochMilli())
                .updateDate(boardPermission.getUpdateDate().toInstant().toEpochMilli())
                .active(boardPermission.isActive())
                .build();
    }

    public static BoardPermission mapToEntity(BoardPermissionDTO boardPermissionDTO) {
        BoardPermission boardPermission = new BoardPermission();
        boardPermission.setId(boardPermissionDTO.getId());
        boardPermission.setBoardId(boardService.mapToEntity(boardPermissionDTO.getBoard()));
        boardPermission.setPermissionType(dictMapper.mapPermissionTypesToEntity(boardPermissionDTO.getPermissionType()));
        boardPermission.setUserId(userMapper.mapToEntity(boardPermissionDTO.getUser()));
        boardPermission.setCreateDate(new Timestamp(boardPermissionDTO.getCreateDate()));
        boardPermission.setUpdateDate(new Timestamp(boardPermissionDTO.getUpdateDate()));
        boardPermission.setActive(boardPermissionDTO.isActive());
        return boardPermission;
    }
}
