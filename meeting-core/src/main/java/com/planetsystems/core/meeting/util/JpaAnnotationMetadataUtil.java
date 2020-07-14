package com.planetsystems.core.meeting.util;

import java.io.Serializable;

import javax.persistence.Entity;

import com.googlecode.genericdao.search.Metadata;
import com.googlecode.genericdao.search.MetadataUtil;

public class JpaAnnotationMetadataUtil implements MetadataUtil {

	public Metadata get(Class<?> klass) throws IllegalArgumentException {
		return JpaAnnotationMetadata.getMetadata(klass);
	}

	public Metadata get(Class<?> rootEntityClass, String propertyPath)
			throws IllegalArgumentException {
		Metadata md = get(rootEntityClass);
		if (propertyPath == null || propertyPath.equals("")) {
			return md;
		} else {
			for (String prop : propertyPath.split("\\.")) {
				if ("id".equals(prop)) {
					md = md.getIdType();
				} else {
					md = md.getPropertyType(prop);
				}
				if (md == null)
					throw new IllegalArgumentException("Property path '"
							+ propertyPath + "' invalid for type "
							+ rootEntityClass.getName());
			}
			return md;
		}
	}

	public Serializable getId(Object object) {
		Metadata md = get(object.getClass());
		return md.getIdValue(object);
	}

	public boolean isId(Class<?> rootClass, String propertyPath) {
		if (propertyPath == null || "".equals(propertyPath))
			return false;

		if (propertyPath.equals("id")
				|| (propertyPath.endsWith(".id") && get(rootClass,
						propertyPath.substring(0, propertyPath.length() - 3))
						.isEntity()))
			return true;

		// see if the property is the identifier property of the entity it
		// belongs to.
		int pos = propertyPath.lastIndexOf(".");
		if (pos != -1) {
			Metadata parentType = get(rootClass, propertyPath.substring(0, pos));
			if (!parentType.isEntity())
				return false;
			return propertyPath.substring(pos + 1).equals(
					parentType.getIdProperty());
		} else {
			return propertyPath.equals(get(rootClass).getIdProperty());
		}
	}

	@SuppressWarnings("unchecked")
	public <T> Class<T> getUnproxiedClass(Class<?> klass) {
		while (klass.getAnnotation(Entity.class) == null) {
			klass = klass.getSuperclass();
			if (Object.class.equals(klass))
				return null;
		}

		return (Class<T>) klass;
	}

	@SuppressWarnings("unchecked")
	public <T> Class<T> getUnproxiedClass(Object entity) {
		return (Class<T>) getUnproxiedClass(entity.getClass());
	}
}
