package drtools.loader.application.port.out.input;

import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.domain.cdi.NamespaceCdi;

import java.util.List;

public interface InputNamespaceCdiProvider {
    List<NamespaceCdi> getNamespaceCdis() throws InputLoadingException;
}
