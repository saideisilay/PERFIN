package edu.iu.perfin.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import com.avaje.ebean.Expression;
import com.avaje.ebean.Model;
import com.avaje.ebean.OrderBy.Property;
import com.avaje.ebean.Query;
import com.avaje.ebeaninternal.server.expression.SimpleExpression;

import edu.iu.perfin.type.IncomeExpense;
import edu.iu.perfin.util.CollectionUtils;
import edu.iu.perfin.util.StringUtil;

@Service
public class GeneralService {

	private static Logger logger = Logger.getLogger(GeneralService.class.getName());

	public static <T, I> T searchById(Class<T> clazz ,I id) {
		if(id == null) return null;
		return (T) Ebean.find(clazz, id);
	}
	
	public static <T> T getFirstByColumn(Class<T> clazz, String columns, Expression... exprs) {
		Query<T> query = generateQuery(clazz, 0, 1, null, Arrays.asList(exprs), columns);
		List<T> findList = query.findList();
		if(CollectionUtils.isNotEmpty(findList)) return findList.get(0);
		return null;
	}
	
	public static <T> T getFirstByColumn(Class<T> clazz, String columns, List<Property> sorterList, Expression... exprs) {
		Query<T> query = generateQuery(clazz, 0, 1, sorterList, Arrays.asList(exprs), columns);
		List<T> findList = query.findList();
		if(CollectionUtils.isNotEmpty(findList)) return findList.get(0);
		return null;
	}

	public static <T> List<T> load(Class<T> clazz, Integer start, Integer pageSize, String columns, List<Property> sorterList, Expression... exprs) {
		Query<T> query = generateQuery(clazz, start, pageSize, sorterList, Arrays.asList(exprs), columns);
		return query.findList();
	}

	public static <T> List<T> load(Class<T> clazz,  Expression... exprs) {
		Query<T> query = generateQuery(clazz, null, null, null, Arrays.asList(exprs), null);
		return query.findList();
	}
	
	public static <T> List<T> loadAll(Class<T> clazz) {
		Query<T> query = generateQuery(clazz, null, null, null, null, null);
		return query.findList();
	}

	public static <T> T getFirstByColumn(Class<T> clazz, Expression... exprs) {
		Query<T> query = generateQuery(clazz, 0, 1, null, Arrays.asList(exprs), null);
		List<T> findList = query.findList();
		if(CollectionUtils.isNotEmpty(findList)) return findList.get(0);
		return null;
	}

	/**
	 * eklenen ve silinen listeleri doner.
	 * @param <T>
	 * */
	public static <T> List<List<T>> merge(List<T> beforeModels, List<T> afterModels) {
		List<T> deleteModels = new ArrayList<T>();
		List<T> updateModels = new ArrayList<T>();
		List<T> addModels   = new ArrayList<T>();

		for (Iterator<T> iterator = beforeModels.iterator(); iterator.hasNext();) {
			T m = (T) iterator.next();

			if ( ! (afterModels.contains(m) ) ) {
				deleteModels.add(m);
			} else {
				int afterModelIndex = afterModels.indexOf(m);
				updateModels.add(afterModels.get(afterModelIndex));
			}
		}

		for (Iterator<T> iterator = afterModels.iterator(); iterator.hasNext();) {
			T m = (T) iterator.next();

			if ( ! (beforeModels.contains(m) ) ) {
				addModels.add(m);
			} 
		}

		updateModels.addAll(addModels);
		for(T model : deleteModels) ((Model) model).delete();
		for(T model : updateModels) ((Model) model).save();

		return Arrays.asList(addModels, deleteModels, updateModels);
	}

	public static <T> Query<T> generateQuery(Class<T> clazz, Integer first, Integer pageSize, List<Property> sorterList, List<Expression> exprs, String selectColumns) {
		Query<T> query = Ebean.createQuery(clazz);
		if(query == null) return null;

		if(StringUtil.isNotBlank(selectColumns)) query.select(selectColumns);

		if(sorterList != null) {
			String orderClause = "";
			Set<String> props = new HashSet<String>();
			for(Property pr : sorterList) {
				if(props.contains(pr.getProperty())) continue;
				else props.add(pr.getProperty());
				boolean string = isPropertyTypeString(clazz, pr.getProperty());
				if(pr.isAscending()) {
					if(!string) orderClause += pr.getProperty() +" asc, ";
					else orderClause +="NLSSORT("+pr.getProperty()+",'NLS_SORT=XTURKISH') asc,";
				} else {
					if(!string) orderClause += pr.getProperty() +" desc, ";
					else orderClause +="NLSSORT("+pr.getProperty()+",'NLS_SORT=XTURKISH') desc,";
				}	
			}
			if(StringUtil.isNotEmpty(orderClause)) query.order(StringUtil.removeLastCharacter(orderClause));
		}
		if(CollectionUtils.isNotEmpty(exprs)) {
			for (Expression expr : exprs) {
		
				query.where(expr);
			}

		};

		if(first != null) query.setFirstRow(first);
		if(pageSize != null) query.setMaxRows(pageSize);
		return query;
	}

	private static boolean isPropertyTypeString(Class clazz, String property) {
		Field[] fs = clazz.getDeclaredFields();
		for (Field field : fs) {
			if(property.equals(field.getName())) {
				if(String.class.equals(field.getType())) return true;
			}
		}
		return false;
	}

}
