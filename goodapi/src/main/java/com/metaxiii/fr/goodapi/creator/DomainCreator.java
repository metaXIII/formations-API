package com.metaxiii.fr.goodapi.creator;

public interface DomainCreator<E, M> {
    E toDomain(final M m);
}
