package it.epicode.gestioneViaggi.cloudinary;

import com.cloudinary.Cloudinary;
import it.epicode.gestioneViaggi.payload.dipendenteDTO.DipendenteResponseDTO;
import it.epicode.gestioneViaggi.service.DipendenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class CloudinaryController {

    private final Cloudinary cloudinary;
    private final DipendenteService dipendenteService;

    @PatchMapping(
            path = "/dipendenti/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public DipendenteResponseDTO uploadAvatar(
            @PathVariable Long id,
            @RequestPart("file") MultipartFile file
    ) throws IOException {

        Map<?,?> result = cloudinary.uploader()
                .upload(
                        file.getBytes(),
                        Cloudinary.asMap(
                                "folder",    "dipendenti",
                                "public_id", "dipendenti_" + id
                        )
                );


        String imageUrl = result.get("secure_url").toString();


        return dipendenteService.uploadAvatar(id, imageUrl);
    }
}
