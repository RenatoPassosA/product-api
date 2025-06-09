package com.project.produtosapi.specification;

@FunctionalInterface
public interface Specification<T> {
    boolean isSatisfiedBy(T t);

    default Specification<T> and(Specification<T> other) {
        return t -> this.isSatisfiedBy(t) && other.isSatisfiedBy(t);
    }

    default Specification<T> or(Specification<T> other) {
        return t -> this.isSatisfiedBy(t) || other.isSatisfiedBy(t);
    }

    default Specification<T> not() {
        return t -> !this.isSatisfiedBy(t);
    }
}

/*
anotações:
	a ideia aqui é eu criar uma interface com o isSatisfiedBy() e criar quantas classes necessarias forem para cada regra de negocio que eu for criar
	os metodos default permitem que se combine regras de forma fluente, sem criar novas classes manualmente.
	@FunctionalInterface - indica que a interface é funcional, ou seja, deve conter apenas um método abstrato
	<T> torna a interface flexível para validar qualquer tipo de objeto
 */
