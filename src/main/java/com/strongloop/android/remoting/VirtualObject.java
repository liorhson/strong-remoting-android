// Copyright (c) 2013 StrongLoop. All rights reserved.

package com.strongloop.android.remoting;

import java.util.Map;

import com.strongloop.android.remoting.adapters.Adapter;

/**
 * A local representative of a single virtual object. The behavior of this
 * object is defined through a model defined on the server, and the identity
 * of this instance is defined through its `creationParameters`.
 */
public class VirtualObject {

    private Repository repository;
    private Map<String, ? extends Object> creationParameters;

    /**
     * Creates a new object from the given repository and parameters.
     * @param repository The repository this object should inherit from.
     * @param creationParameters The creationParameters of the new object.
     */
    public VirtualObject(Repository repository,
    		Map<String, ? extends Object> creationParameters) {
        setRepository(repository);
        setCreationParameters(creationParameters);
    }

    /**
     * Gets the {@link Repository} this object was created from.
     * @return the {@link Repository}.
     */
    public Repository getRepository() {
        return repository;
    }

    /**
     * Sets the {@link Repository} this object was created from.
     * @param repository The {@link Repository}.
     */
    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    /**
     * Gets the creation parameters this object was created from.
     * @return the creation parameters.
     */
    public Map<String, ? extends Object> getCreationParameters() {
        return creationParameters;
    }

    /**
     * Sets the creation parameters this object was created from.
     * @param creationParameters The creation parameters.
     */
    public void setCreationParameters(
    		Map<String, ? extends Object> creationParameters) {
        this.creationParameters = creationParameters;
    }

    /**
     * Invokes a remotable method exposed within instances of this class on the
     * server.
     * @param method The method to invoke (without the repository), e.g.
     * <code>"doSomething"</code>.
     * @param parameters The parameters to invoke with.
     * @param callback The callback to invoke when the execution finishes.
     */
    public void invokeMethod(String method,
            Map<String, ? extends Object> parameters,
            Adapter.Callback callback) {
        Adapter adapter = repository.getAdapter();
        if (adapter == null) {
            throw new IllegalArgumentException(
                    "Repository adapter cannot be null");
        }
        String path = repository.getClassName() + ".prototype." + method;
        adapter.invokeInstanceMethod(path, creationParameters, parameters,
        		callback);
    }
}
