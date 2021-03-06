package core.services;

import core.transformers.InquiryEntityDtoTransformer;
import db.entity.InquiryEntity;
import db.repository.InquiryRepositoryDAO;
import model.InquiryEntityDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class InquiryEntityService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final InquiryRepositoryDAO inquiryEntityRepository;

    private final InquiryEntityDtoTransformer inquiryEntityDtoTransformer;

    public InquiryEntityService(final InquiryRepositoryDAO inquiryEntityRepository, final InquiryEntityDtoTransformer inquiryEntityDtoTransformer) {
        this.inquiryEntityRepository = inquiryEntityRepository;
        this.inquiryEntityDtoTransformer = inquiryEntityDtoTransformer;
    }

    public InquiryEntityDto getInquiryEntity(final Long gid) {
        return inquiryEntityDtoTransformer.generate(inquiryEntityRepository.findOneByGid(gid));
    }

    public InquiryEntityDto createInquiryEntity(final InquiryEntityDto inquiryEntityDto) {
        InquiryEntity inquiryEntity = inquiryEntityRepository.saveAndFlush(inquiryEntityDtoTransformer.generate(inquiryEntityDto));
        return inquiryEntityDtoTransformer.generate(inquiryEntity);
    }
}
