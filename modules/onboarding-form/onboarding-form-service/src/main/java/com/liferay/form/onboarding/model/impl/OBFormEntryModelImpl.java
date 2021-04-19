/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.form.onboarding.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.form.onboarding.model.OBFormEntry;
import com.liferay.form.onboarding.model.OBFormEntryModel;
import com.liferay.form.onboarding.model.OBFormEntrySoap;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the OBFormEntry service. Represents a row in the &quot;OBForm_OBFormEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>OBFormEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link OBFormEntryImpl}.
 * </p>
 *
 * @author Evan Thibodeau
 * @see OBFormEntryImpl
 * @generated
 */
@JSON(strict = true)
public class OBFormEntryModelImpl
	extends BaseModelImpl<OBFormEntry> implements OBFormEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a ob form entry model instance should use the <code>OBFormEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "OBForm_OBFormEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"obFormEntryId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"name", Types.VARCHAR}, {"formId", Types.BIGINT},
		{"roleIds", Types.VARCHAR}, {"siteIds", Types.VARCHAR},
		{"active_", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("obFormEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("formId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("roleIds", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("siteIds", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("active_", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table OBForm_OBFormEntry (uuid_ VARCHAR(75) null,obFormEntryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,name VARCHAR(75) null,formId LONG,roleIds VARCHAR(75) null,siteIds VARCHAR(75) null,active_ BOOLEAN)";

	public static final String TABLE_SQL_DROP = "drop table OBForm_OBFormEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY obFormEntry.createDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY OBForm_OBFormEntry.createDate DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long NAME_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)
	 */
	@Deprecated
	public static final long CREATEDATE_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
	}

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static OBFormEntry toModel(OBFormEntrySoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		OBFormEntry model = new OBFormEntryImpl();

		model.setUuid(soapModel.getUuid());
		model.setObFormEntryId(soapModel.getObFormEntryId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setName(soapModel.getName());
		model.setFormId(soapModel.getFormId());
		model.setRoleIds(soapModel.getRoleIds());
		model.setSiteIds(soapModel.getSiteIds());
		model.setActive(soapModel.isActive());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static List<OBFormEntry> toModels(OBFormEntrySoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<OBFormEntry> models = new ArrayList<OBFormEntry>(
			soapModels.length);

		for (OBFormEntrySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public OBFormEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _obFormEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setObFormEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _obFormEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return OBFormEntry.class;
	}

	@Override
	public String getModelClassName() {
		return OBFormEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<OBFormEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<OBFormEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<OBFormEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((OBFormEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<OBFormEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<OBFormEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(OBFormEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<OBFormEntry, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<OBFormEntry, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, OBFormEntry>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			OBFormEntry.class.getClassLoader(), OBFormEntry.class,
			ModelWrapper.class);

		try {
			Constructor<OBFormEntry> constructor =
				(Constructor<OBFormEntry>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private static final Map<String, Function<OBFormEntry, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<OBFormEntry, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<OBFormEntry, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<OBFormEntry, Object>>();
		Map<String, BiConsumer<OBFormEntry, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<OBFormEntry, ?>>();

		attributeGetterFunctions.put("uuid", OBFormEntry::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<OBFormEntry, String>)OBFormEntry::setUuid);
		attributeGetterFunctions.put(
			"obFormEntryId", OBFormEntry::getObFormEntryId);
		attributeSetterBiConsumers.put(
			"obFormEntryId",
			(BiConsumer<OBFormEntry, Long>)OBFormEntry::setObFormEntryId);
		attributeGetterFunctions.put("groupId", OBFormEntry::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId", (BiConsumer<OBFormEntry, Long>)OBFormEntry::setGroupId);
		attributeGetterFunctions.put("companyId", OBFormEntry::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<OBFormEntry, Long>)OBFormEntry::setCompanyId);
		attributeGetterFunctions.put("userId", OBFormEntry::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<OBFormEntry, Long>)OBFormEntry::setUserId);
		attributeGetterFunctions.put("userName", OBFormEntry::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<OBFormEntry, String>)OBFormEntry::setUserName);
		attributeGetterFunctions.put("createDate", OBFormEntry::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<OBFormEntry, Date>)OBFormEntry::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", OBFormEntry::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<OBFormEntry, Date>)OBFormEntry::setModifiedDate);
		attributeGetterFunctions.put("name", OBFormEntry::getName);
		attributeSetterBiConsumers.put(
			"name", (BiConsumer<OBFormEntry, String>)OBFormEntry::setName);
		attributeGetterFunctions.put("formId", OBFormEntry::getFormId);
		attributeSetterBiConsumers.put(
			"formId", (BiConsumer<OBFormEntry, Long>)OBFormEntry::setFormId);
		attributeGetterFunctions.put("roleIds", OBFormEntry::getRoleIds);
		attributeSetterBiConsumers.put(
			"roleIds",
			(BiConsumer<OBFormEntry, String>)OBFormEntry::setRoleIds);
		attributeGetterFunctions.put("siteIds", OBFormEntry::getSiteIds);
		attributeSetterBiConsumers.put(
			"siteIds",
			(BiConsumer<OBFormEntry, String>)OBFormEntry::setSiteIds);
		attributeGetterFunctions.put("active", OBFormEntry::getActive);
		attributeSetterBiConsumers.put(
			"active", (BiConsumer<OBFormEntry, Boolean>)OBFormEntry::setActive);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_uuid = uuid;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalUuid() {
		return getColumnOriginalValue("uuid_");
	}

	@JSON
	@Override
	public long getObFormEntryId() {
		return _obFormEntryId;
	}

	@Override
	public void setObFormEntryId(long obFormEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_obFormEntryId = obFormEntryId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_groupId = groupId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalGroupId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("groupId"));
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_companyId = companyId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCompanyId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("companyId"));
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_name = name;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalName() {
		return getColumnOriginalValue("name");
	}

	@JSON
	@Override
	public long getFormId() {
		return _formId;
	}

	@Override
	public void setFormId(long formId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_formId = formId;
	}

	@JSON
	@Override
	public String getRoleIds() {
		if (_roleIds == null) {
			return "";
		}
		else {
			return _roleIds;
		}
	}

	@Override
	public void setRoleIds(String roleIds) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_roleIds = roleIds;
	}

	@JSON
	@Override
	public String getSiteIds() {
		if (_siteIds == null) {
			return "";
		}
		else {
			return _siteIds;
		}
	}

	@Override
	public void setSiteIds(String siteIds) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_siteIds = siteIds;
	}

	@JSON
	@Override
	public boolean getActive() {
		return _active;
	}

	@JSON
	@Override
	public boolean isActive() {
		return _active;
	}

	@Override
	public void setActive(boolean active) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_active = active;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(OBFormEntry.class.getName()));
	}

	public long getColumnBitmask() {
		if (_columnBitmask > 0) {
			return _columnBitmask;
		}

		if ((_columnOriginalValues == null) ||
			(_columnOriginalValues == Collections.EMPTY_MAP)) {

			return 0;
		}

		for (Map.Entry<String, Object> entry :
				_columnOriginalValues.entrySet()) {

			if (entry.getValue() != getColumnValue(entry.getKey())) {
				_columnBitmask |= _columnBitmasks.get(entry.getKey());
			}
		}

		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), OBFormEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public OBFormEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, OBFormEntry>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		OBFormEntryImpl obFormEntryImpl = new OBFormEntryImpl();

		obFormEntryImpl.setUuid(getUuid());
		obFormEntryImpl.setObFormEntryId(getObFormEntryId());
		obFormEntryImpl.setGroupId(getGroupId());
		obFormEntryImpl.setCompanyId(getCompanyId());
		obFormEntryImpl.setUserId(getUserId());
		obFormEntryImpl.setUserName(getUserName());
		obFormEntryImpl.setCreateDate(getCreateDate());
		obFormEntryImpl.setModifiedDate(getModifiedDate());
		obFormEntryImpl.setName(getName());
		obFormEntryImpl.setFormId(getFormId());
		obFormEntryImpl.setRoleIds(getRoleIds());
		obFormEntryImpl.setSiteIds(getSiteIds());
		obFormEntryImpl.setActive(isActive());

		obFormEntryImpl.resetOriginalValues();

		return obFormEntryImpl;
	}

	@Override
	public int compareTo(OBFormEntry obFormEntry) {
		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), obFormEntry.getCreateDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof OBFormEntry)) {
			return false;
		}

		OBFormEntry obFormEntry = (OBFormEntry)object;

		long primaryKey = obFormEntry.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isEntityCacheEnabled() {
		return true;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return true;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_setModifiedDate = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<OBFormEntry> toCacheModel() {
		OBFormEntryCacheModel obFormEntryCacheModel =
			new OBFormEntryCacheModel();

		obFormEntryCacheModel.uuid = getUuid();

		String uuid = obFormEntryCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			obFormEntryCacheModel.uuid = null;
		}

		obFormEntryCacheModel.obFormEntryId = getObFormEntryId();

		obFormEntryCacheModel.groupId = getGroupId();

		obFormEntryCacheModel.companyId = getCompanyId();

		obFormEntryCacheModel.userId = getUserId();

		obFormEntryCacheModel.userName = getUserName();

		String userName = obFormEntryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			obFormEntryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			obFormEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			obFormEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			obFormEntryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			obFormEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		obFormEntryCacheModel.name = getName();

		String name = obFormEntryCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			obFormEntryCacheModel.name = null;
		}

		obFormEntryCacheModel.formId = getFormId();

		obFormEntryCacheModel.roleIds = getRoleIds();

		String roleIds = obFormEntryCacheModel.roleIds;

		if ((roleIds != null) && (roleIds.length() == 0)) {
			obFormEntryCacheModel.roleIds = null;
		}

		obFormEntryCacheModel.siteIds = getSiteIds();

		String siteIds = obFormEntryCacheModel.siteIds;

		if ((siteIds != null) && (siteIds.length() == 0)) {
			obFormEntryCacheModel.siteIds = null;
		}

		obFormEntryCacheModel.active = isActive();

		return obFormEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<OBFormEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(4 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<OBFormEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<OBFormEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((OBFormEntry)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<OBFormEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<OBFormEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<OBFormEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((OBFormEntry)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, OBFormEntry>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private String _uuid;
	private long _obFormEntryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _name;
	private long _formId;
	private String _roleIds;
	private String _siteIds;
	private boolean _active;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<OBFormEntry, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((OBFormEntry)this);
	}

	public <T> T getColumnOriginalValue(String columnName) {
		if (_columnOriginalValues == null) {
			return null;
		}

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		return (T)_columnOriginalValues.get(columnName);
	}

	private void _setColumnOriginalValues() {
		_columnOriginalValues = new HashMap<String, Object>();

		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put("obFormEntryId", _obFormEntryId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("name", _name);
		_columnOriginalValues.put("formId", _formId);
		_columnOriginalValues.put("roleIds", _roleIds);
		_columnOriginalValues.put("siteIds", _siteIds);
		_columnOriginalValues.put("active_", _active);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");
		attributeNames.put("active_", "active");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("uuid_", 1L);

		columnBitmasks.put("obFormEntryId", 2L);

		columnBitmasks.put("groupId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("userId", 16L);

		columnBitmasks.put("userName", 32L);

		columnBitmasks.put("createDate", 64L);

		columnBitmasks.put("modifiedDate", 128L);

		columnBitmasks.put("name", 256L);

		columnBitmasks.put("formId", 512L);

		columnBitmasks.put("roleIds", 1024L);

		columnBitmasks.put("siteIds", 2048L);

		columnBitmasks.put("active_", 4096L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private OBFormEntry _escapedModel;

}