package drtools.loader.application.port.out.input;

import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.domain.cdi.MethodCdi;

import java.util.List;

public interface InputMethodCdiProvider {
    List<MethodCdi> getMethodCdis() throws InputLoadingException;
}
