package drtools.loader.application.port.out.input;

import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.domain.cdi.TypeCdi;

import java.util.List;

public interface InputTypeCdiProvider {
    List<TypeCdi> getTypeCdis() throws InputLoadingException;
}
