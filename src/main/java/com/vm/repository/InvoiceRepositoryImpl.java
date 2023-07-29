package com.vm.repository;

import com.vm.dto.Datatable;
import com.vm.dto.InvoiceDto;
import com.vm.dto.InvoiceRequest;
import com.vm.dto.PageDto;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InvoiceRepositoryImpl extends BaseRepository implements InvoiceCustomRepository {
    @Override
    public Datatable getListInvoicePage(InvoiceRequest invoiceRequest) {
        PageDto pageDTO = sqlSearch(invoiceRequest);
        return getListDataTableBySqlQuery(pageDTO.getSqlQuery(),
                pageDTO.getParameters(), invoiceRequest.getPage(), invoiceRequest.getPageSize(),
                InvoiceDto.class,
                invoiceRequest.getSortName(), invoiceRequest.getSortType());
    }

    private PageDto sqlSearch(InvoiceRequest invoiceRequest) {
        PageDto pageDTO = new PageDto();
        Map<String, Object> parameter = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT\n" +
                "i.invoice_id invoiceId,\n" +
                "i.total_amount totalAmount,\n" +
                "i.status status,\n" +
                "i.note note,\n" +
                "i.created_by createdBy,\n" +
                "i.created_at createdAt,\n" +
                "i.updated_by updatedBy,\n" +
                "i.updated_date updateAt,\n" +
                "i.user_id userId\n" +
                "FROM invoice i\n" +
                "WHERE 1 = 1 ");
        if (invoiceRequest != null) {
            if (invoiceRequest.getStatus() != null) {
                sql.append(" And i.status = :status ");
                parameter.put("status", invoiceRequest.getStatus());
            }
        }
        sql.append(" ORDER BY i.invoice_id ASC ");
        pageDTO.setSqlQuery(sql.toString());
        pageDTO.setParameters(parameter);
        return pageDTO;
    }
}
