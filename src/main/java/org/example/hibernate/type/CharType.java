package org.example.hibernate.type;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StringType;
import org.hibernate.type.descriptor.sql.CharTypeDescriptor;
import org.hibernate.usertype.UserType;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedSuperclass
public class CharType implements UserType
{
  @Override
  public Object assemble(final Serializable cached, final Object owner) throws HibernateException
  {
    return cached;
  }

  /**
   * @see org.hibernate.usertype.UserType#deepCopy(java.lang.Object)
   */
  @Override
  public Object deepCopy(Object value) throws HibernateException
  {
    if (value == null)
    {
      return null;
    }

    return (String) value;
  }

  @Override
  public Serializable disassemble(Object value) throws HibernateException
  {
    return (Serializable) value;
  }

  @Override
  public boolean isMutable()
  {
    return false;
  }

  @Override
  public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException
  {
    final String value = (String) StringType.INSTANCE.get(rs, names[0], session);

    if (value == null)
    {
      return null;
    }

    return value.trim();
  }

  @Override
  public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException
  {
    String result = value == null ? null : value.toString();

    if (result != null)
    {
      result = String.format("%1$-" + st.getParameterMetaData().getPrecision(index) + "s", result);
    }

    StringType.INSTANCE.set(st, result, index, session);
  }

  @Override
  public Object replace(Object original, Object target, Object owner) throws HibernateException
  {
    return original;
  }

  @Override
  public Class<String> returnedClass()
  {
    return String.class;
  }

  @Override
  public int[] sqlTypes()
  {
    return new int[] { CharTypeDescriptor.INSTANCE.getSqlType() };
  }

  @Override
  public boolean equals(Object x, Object y) throws HibernateException
  {
    return x.equals(y);
  }

  @Override
  public int hashCode(Object x) throws HibernateException
  {
    return x.hashCode();
  }
}
