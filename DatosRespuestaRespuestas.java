package com.exemple.forohub.DTO;

import com.exemple.forohub.enumerador.TipoPerfil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRespuestaRespuestas(
        @NotNull Long id,
        @NotBlank String mensaje,
        @NotNull String fechaCreacion,
        Boolean solucion,
        @NotBlank String nombreAutor,
        @NotNull TipoPerfil perfilAutor,
        @NotNull String tituloTopico) {

}
