package com.vm.repository.hibernates;

import com.vm.dto.BaseDto;
import com.vm.dto.Datatable;
import com.vm.dto.InvoiceDto;
import com.vm.dto.InvoiceRequest;
import com.vm.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InvoiceRepositoryImpl extends BaseRepository implements InvoiceRepo {
    @Override
    public Datatable getListInvoicePage(InvoiceRequest invoiceRequest) {
        BaseDto baseDTO = sqlSearch(invoiceRequest);
        return getListDataTableBySqlQuery(baseDTO.getSqlQuery(),
                baseDTO.getParameters(), invoiceRequest.getPage(), invoiceRequest.getPageSize(),
                InvoiceDto.class,
                invoiceRequest.getSortName(), invoiceRequest.getSortType());
    }

    private BaseDto sqlSearch(InvoiceRequest invoiceRequest) {
        BaseDto baseDTO = new BaseDto();
        Map<String, Object> parameter = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT\n" +
                "i.invoice_id invoiceId,\n" +
                "i.total_amount totalAmount,\n" +
                "i.status status,\n" +
                "i.note note,\n" +
                "i.created_by createdBy,\n" +
                "i.created_date createdDate,\n" +
                "i.updated_by updatedBy,\n" +
                "i.updated_date ,\n" +
                "i.user_id \n" +
                "FROM invoice i\n" +
                "WHERE 1 = 1 ");
        if (invoiceRequest != null) {
            if (invoiceRequest.getStatus() != null) {
                sql.append(" And i.status = :status ");
                parameter.put("status", invoiceRequest.getStatus());
            }
        }
        sql.append(" ORDER BY i.invoice_id ASC ");
        baseDTO.setSqlQuery(sql.toString());
        baseDTO.setParameters(parameter);
        return baseDTO;
    }
}
