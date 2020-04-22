package com.trzewik.information.consumer.infrastructure.rest.information;

import com.hltech.pact.gen.domain.client.feign.InteractionInfo;
import com.trzewik.information.consumer.domain.information.Information;
import com.trzewik.information.consumer.domain.information.InformationClient;
import com.trzewik.information.consumer.domain.information.InformationService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * You can create your FeignConfiguration class for defining some behaviour, for example:
 * - ErrorDecoder - for some error handling
 * {@link feign.codec.Decoder}, {@link feign.codec.Encoder}, {@link feign.Contract}
 *
 * @see FeignClientsConfiguration for the defaults
 */
@FeignClient(name = "information-producer", url = "${url.information.producer}")
public interface InformationProducerClient extends InformationClient {

    default Information get(String id) {
        return getInformation(id).toInformation();
    }

    @InteractionInfo(responseStatus = HttpStatus.OK)
    @GetMapping(value = "/information/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    InformationDto getInformation(@PathVariable(value = "id") String id);

    default Information create(InformationService.InformationForm form) {
        return createInformation(form).toInformation();
    }

    @InteractionInfo(responseStatus = HttpStatus.CREATED)
    @PostMapping(value = "/information", consumes = MediaType.APPLICATION_JSON_VALUE)
    InformationDto createInformation(@RequestBody InformationService.InformationForm form);

    default Information replace(String id, InformationService.InformationForm form) {
        return replaceInformation(id, form).toInformation();
    }

    @InteractionInfo(responseStatus = HttpStatus.OK)
    @PutMapping(value = "/information/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    InformationDto replaceInformation(
        @PathVariable(value = "id") String id,
        @RequestBody InformationService.InformationForm form
    );

    default Information update(String id, InformationService.InformationForm form) {
        return updateInformation(id, form).toInformation();
    }

    @InteractionInfo(responseStatus = HttpStatus.OK)
    @PatchMapping(value = "/information/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    InformationDto updateInformation(
        @PathVariable(value = "id") String id,
        @RequestBody InformationService.InformationForm form
    );

    default Information delete(String id) {
        return deleteInformation(id).toInformation();
    }

    @InteractionInfo(responseStatus = HttpStatus.OK)
    @DeleteMapping(value = "/information/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    InformationDto deleteInformation(@PathVariable(value = "id") String id);
}
