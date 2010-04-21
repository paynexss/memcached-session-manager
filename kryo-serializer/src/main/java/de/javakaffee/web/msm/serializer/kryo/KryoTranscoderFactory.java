/*
 * Copyright 2009 Martin Grotzke
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an &quot;AS IS&quot; BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package de.javakaffee.web.msm.serializer.kryo;

import org.apache.catalina.Manager;

import de.javakaffee.web.msm.SessionAttributesTranscoder;
import de.javakaffee.web.msm.SessionTranscoder;
import de.javakaffee.web.msm.TranscoderFactory;

/**
 * Creates a {@link KryoTranscoder}.
 *
 * @author <a href="mailto:martin.grotzke@javakaffee.de">Martin Grotzke</a>
 */
public class KryoTranscoderFactory implements TranscoderFactory {

    private boolean _copyCollectionsForSerialization;
    private String[] _customConverterClassNames;
    private KryoTranscoder _transcoder;

    /**
     * {@inheritDoc}
     */
    public SessionAttributesTranscoder createTranscoder( final Manager manager ) {
        return getTranscoder( manager );
    }

    /**
     * Gets/creates a single instance of {@link JavolutionTranscoder}. We need to have a single
     * instance so that {@link XMLFormat}s are not created twice which would lead to errors.
     *
     * @param manager the manager that will be passed to the transcoder.
     * @return for all invocations the same instance of {@link JavolutionTranscoder}.
     */
    private KryoTranscoder getTranscoder( final Manager manager ) {
        if ( _transcoder == null ) {
            _transcoder = new KryoTranscoder( manager.getContainer().getLoader().getClassLoader(),
                    _customConverterClassNames, _copyCollectionsForSerialization );
        }
        return _transcoder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SessionTranscoder createSessionTranscoder( final Manager manager ) {
        return getTranscoder( manager );
    }

    /**
     * {@inheritDoc}
     */
    public void setCopyCollectionsForSerialization( final boolean copyCollectionsForSerialization ) {
        _copyCollectionsForSerialization = copyCollectionsForSerialization;
    }

    /**
     * {@inheritDoc}
     */
    public void setCustomConverterClassNames( final String[] customConverterClassNames ) {
        _customConverterClassNames = customConverterClassNames;
    }

}
