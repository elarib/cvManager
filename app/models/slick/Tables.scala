package models.slick
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.driver.MySQLDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.driver.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{ GetResult => GR }

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Array(Competence.schema, CompetencesSkills.schema, Component.schema, Education.schema, ElementCompetence.schema, Objectif.schema, Role.schema, Skill.schema, User.schema, UserInfo.schema, WorkExperience.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /**
   * Entity class storing rows of table Competence
   *  @param id Database column id SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(VARCHAR), Length(255,true), Default(None)
   */
  case class CompetenceRow(id: Long, name: Option[String] = None)
  /** GetResult implicit for fetching CompetenceRow objects using plain SQL queries */
  implicit def GetResultCompetenceRow(implicit e0: GR[Long], e1: GR[Option[String]]): GR[CompetenceRow] = GR {
    prs =>
      import prs._
      CompetenceRow.tupled((<<[Long], <<?[String]))
  }
  /** Table description of table competence. Objects of this class serve as prototypes for rows in queries. */
  class Competence(_tableTag: Tag) extends Table[CompetenceRow](_tableTag, "competence") {
    def * = (id, name) <> (CompetenceRow.tupled, CompetenceRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), name).shaped.<>({ r => import r._; _1.map(_ => CompetenceRow.tupled((_1.get, _2))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(VARCHAR), Length(255,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("name", O.Length(255, varying = true), O.Default(None))
  }
  /** Collection-like TableQuery object for table Competence */
  lazy val Competence = new TableQuery(tag => new Competence(tag))

  /**
   * Entity class storing rows of table CompetencesSkills
   *  @param idCompetence Database column id_competence SqlType(BIGINT)
   *  @param idSkill Database column id_skill SqlType(BIGINT)
   */
  case class CompetencesSkillsRow(idCompetence: Long, idSkill: Long)
  /** GetResult implicit for fetching CompetencesSkillsRow objects using plain SQL queries */
  implicit def GetResultCompetencesSkillsRow(implicit e0: GR[Long]): GR[CompetencesSkillsRow] = GR {
    prs =>
      import prs._
      CompetencesSkillsRow.tupled((<<[Long], <<[Long]))
  }
  /** Table description of table competences_skills. Objects of this class serve as prototypes for rows in queries. */
  class CompetencesSkills(_tableTag: Tag) extends Table[CompetencesSkillsRow](_tableTag, "competences_skills") {
    def * = (idCompetence, idSkill) <> (CompetencesSkillsRow.tupled, CompetencesSkillsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(idCompetence), Rep.Some(idSkill)).shaped.<>({ r => import r._; _1.map(_ => CompetencesSkillsRow.tupled((_1.get, _2.get))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column id_competence SqlType(BIGINT) */
    val idCompetence: Rep[Long] = column[Long]("id_competence")
    /** Database column id_skill SqlType(BIGINT) */
    val idSkill: Rep[Long] = column[Long]("id_skill")

    /** Primary key of CompetencesSkills (database name competences_skills_PK) */
    val pk = primaryKey("competences_skills_PK", (idCompetence, idSkill))

    /** Foreign key referencing Competence (database name FK_oyxc3ffos2g4wjrbkwmopsy1) */
    lazy val competenceFk = foreignKey("FK_oyxc3ffos2g4wjrbkwmopsy1", idCompetence, Competence)(r => r.id, onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)
    /** Foreign key referencing Skill (database name FK_sjdl96dsb86nonmt5k18n430s) */
    lazy val skillFk = foreignKey("FK_sjdl96dsb86nonmt5k18n430s", idSkill, Skill)(r => r.id, onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table CompetencesSkills */
  lazy val CompetencesSkills = new TableQuery(tag => new CompetencesSkills(tag))

  /**
   * Entity class storing rows of table Component
   *  @param id Database column id SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param idUser Database column id_user SqlType(BIGINT), Default(None)
   */
  case class ComponentRow(id: Long, name: Option[String] = None, idUser: Option[Long] = None)
  /** GetResult implicit for fetching ComponentRow objects using plain SQL queries */
  implicit def GetResultComponentRow(implicit e0: GR[Long], e1: GR[Option[String]], e2: GR[Option[Long]]): GR[ComponentRow] = GR {
    prs =>
      import prs._
      ComponentRow.tupled((<<[Long], <<?[String], <<?[Long]))
  }
  /** Table description of table component. Objects of this class serve as prototypes for rows in queries. */
  class Component(_tableTag: Tag) extends Table[ComponentRow](_tableTag, "component") {
    def * = (id, name, idUser) <> (ComponentRow.tupled, ComponentRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), name, idUser).shaped.<>({ r => import r._; _1.map(_ => ComponentRow.tupled((_1.get, _2, _3))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(VARCHAR), Length(255,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("name", O.Length(255, varying = true), O.Default(None))
    /** Database column id_user SqlType(BIGINT), Default(None) */
    val idUser: Rep[Option[Long]] = column[Option[Long]]("id_user", O.Default(None))

    /** Foreign key referencing User (database name FK_iag430km1hfnaw2ys80mnwc4h) */
    lazy val userFk = foreignKey("FK_iag430km1hfnaw2ys80mnwc4h", idUser, User)(r => Rep.Some(r.id), onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Component */
  lazy val Component = new TableQuery(tag => new Component(tag))

  /**
   * Entity class storing rows of table Education
   *  @param description Database column description SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param place Database column place SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param yearFrom Database column year_from SqlType(INT)
   *  @param yearTo Database column year_to SqlType(INT)
   *  @param id Database column id SqlType(BIGINT), PrimaryKey
   */
  case class EducationRow(description: Option[String] = None, place: Option[String] = None, yearFrom: Int, yearTo: Int, id: Long)
  /** GetResult implicit for fetching EducationRow objects using plain SQL queries */
  implicit def GetResultEducationRow(implicit e0: GR[Option[String]], e1: GR[Int], e2: GR[Long]): GR[EducationRow] = GR {
    prs =>
      import prs._
      EducationRow.tupled((<<?[String], <<?[String], <<[Int], <<[Int], <<[Long]))
  }
  /** Table description of table education. Objects of this class serve as prototypes for rows in queries. */
  class Education(_tableTag: Tag) extends Table[EducationRow](_tableTag, "education") {
    def * = (description, place, yearFrom, yearTo, id) <> (EducationRow.tupled, EducationRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (description, place, Rep.Some(yearFrom), Rep.Some(yearTo), Rep.Some(id)).shaped.<>({ r => import r._; _3.map(_ => EducationRow.tupled((_1, _2, _3.get, _4.get, _5.get))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column description SqlType(VARCHAR), Length(255,true), Default(None) */
    val description: Rep[Option[String]] = column[Option[String]]("description", O.Length(255, varying = true), O.Default(None))
    /** Database column place SqlType(VARCHAR), Length(255,true), Default(None) */
    val place: Rep[Option[String]] = column[Option[String]]("place", O.Length(255, varying = true), O.Default(None))
    /** Database column year_from SqlType(INT) */
    val yearFrom: Rep[Int] = column[Int]("year_from")
    /** Database column year_to SqlType(INT) */
    val yearTo: Rep[Int] = column[Int]("year_to")
    /** Database column id SqlType(BIGINT), PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.PrimaryKey)

    /** Foreign key referencing Component (database name FK_510u3nm1ji2p2c6hormkfcsh6) */
    lazy val componentFk = foreignKey("FK_510u3nm1ji2p2c6hormkfcsh6", id, Component)(r => r.id, onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Education */
  lazy val Education = new TableQuery(tag => new Education(tag))

  /**
   * Entity class storing rows of table ElementCompetence
   *  @param id Database column id SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param detail Database column detail SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param name Database column name SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param idCompentence Database column id_compentence SqlType(BIGINT), Default(None)
   */
  case class ElementCompetenceRow(id: Long, detail: Option[String] = None, name: Option[String] = None, idCompentence: Option[Long] = None)
  /** GetResult implicit for fetching ElementCompetenceRow objects using plain SQL queries */
  implicit def GetResultElementCompetenceRow(implicit e0: GR[Long], e1: GR[Option[String]], e2: GR[Option[Long]]): GR[ElementCompetenceRow] = GR {
    prs =>
      import prs._
      ElementCompetenceRow.tupled((<<[Long], <<?[String], <<?[String], <<?[Long]))
  }
  /** Table description of table element_competence. Objects of this class serve as prototypes for rows in queries. */
  class ElementCompetence(_tableTag: Tag) extends Table[ElementCompetenceRow](_tableTag, "element_competence") {
    def * = (id, detail, name, idCompentence) <> (ElementCompetenceRow.tupled, ElementCompetenceRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), detail, name, idCompentence).shaped.<>({ r => import r._; _1.map(_ => ElementCompetenceRow.tupled((_1.get, _2, _3, _4))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column detail SqlType(VARCHAR), Length(255,true), Default(None) */
    val detail: Rep[Option[String]] = column[Option[String]]("detail", O.Length(255, varying = true), O.Default(None))
    /** Database column name SqlType(VARCHAR), Length(255,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("name", O.Length(255, varying = true), O.Default(None))
    /** Database column id_compentence SqlType(BIGINT), Default(None) */
    val idCompentence: Rep[Option[Long]] = column[Option[Long]]("id_compentence", O.Default(None))

    /** Foreign key referencing Competence (database name FK_60duxf7964i4b2mpo4k7g589k) */
    lazy val competenceFk = foreignKey("FK_60duxf7964i4b2mpo4k7g589k", idCompentence, Competence)(r => Rep.Some(r.id), onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table ElementCompetence */
  lazy val ElementCompetence = new TableQuery(tag => new ElementCompetence(tag))

  /**
   * Entity class storing rows of table Objectif
   *  @param content Database column content SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param id Database column id SqlType(BIGINT), PrimaryKey
   */
  case class ObjectifRow(content: Option[String] = None, id: Long)
  /** GetResult implicit for fetching ObjectifRow objects using plain SQL queries */
  implicit def GetResultObjectifRow(implicit e0: GR[Option[String]], e1: GR[Long]): GR[ObjectifRow] = GR {
    prs =>
      import prs._
      ObjectifRow.tupled((<<?[String], <<[Long]))
  }
  /** Table description of table objectif. Objects of this class serve as prototypes for rows in queries. */
  class Objectif(_tableTag: Tag) extends Table[ObjectifRow](_tableTag, "objectif") {
    def * = (content, id) <> (ObjectifRow.tupled, ObjectifRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (content, Rep.Some(id)).shaped.<>({ r => import r._; _2.map(_ => ObjectifRow.tupled((_1, _2.get))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column content SqlType(VARCHAR), Length(255,true), Default(None) */
    val content: Rep[Option[String]] = column[Option[String]]("content", O.Length(255, varying = true), O.Default(None))
    /** Database column id SqlType(BIGINT), PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.PrimaryKey)

    /** Foreign key referencing Component (database name FK_cbn4ghd6wadenl15uldatx0lx) */
    lazy val componentFk = foreignKey("FK_cbn4ghd6wadenl15uldatx0lx", id, Component)(r => r.id, onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Objectif */
  lazy val Objectif = new TableQuery(tag => new Objectif(tag))

  /**
   * Entity class storing rows of table Role
   *  @param id Database column id SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param nomRole Database column nom_role SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param idUser Database column id_user SqlType(BIGINT), Default(None)
   */
  case class RoleRow(id: Long, nomRole: Option[String] = None, idUser: Option[Long] = None)
  /** GetResult implicit for fetching RoleRow objects using plain SQL queries */
  implicit def GetResultRoleRow(implicit e0: GR[Long], e1: GR[Option[String]], e2: GR[Option[Long]]): GR[RoleRow] = GR {
    prs =>
      import prs._
      RoleRow.tupled((<<[Long], <<?[String], <<?[Long]))
  }
  /** Table description of table role. Objects of this class serve as prototypes for rows in queries. */
  class Role(_tableTag: Tag) extends Table[RoleRow](_tableTag, "role") {
    def * = (id, nomRole, idUser) <> (RoleRow.tupled, RoleRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), nomRole, idUser).shaped.<>({ r => import r._; _1.map(_ => RoleRow.tupled((_1.get, _2, _3))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column nom_role SqlType(VARCHAR), Length(255,true), Default(None) */
    val nomRole: Rep[Option[String]] = column[Option[String]]("nom_role", O.Length(255, varying = true), O.Default(None))
    /** Database column id_user SqlType(BIGINT), Default(None) */
    val idUser: Rep[Option[Long]] = column[Option[Long]]("id_user", O.Default(None))

    /** Foreign key referencing User (database name FK_6ef3ncawl3rqvadr03pi804um) */
    lazy val userFk = foreignKey("FK_6ef3ncawl3rqvadr03pi804um", idUser, User)(r => Rep.Some(r.id), onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Role */
  lazy val Role = new TableQuery(tag => new Role(tag))

  /**
   * Entity class storing rows of table Skill
   *  @param id Database column id SqlType(BIGINT), PrimaryKey
   */
  case class SkillRow(id: Long)
  /** GetResult implicit for fetching SkillRow objects using plain SQL queries */
  implicit def GetResultSkillRow(implicit e0: GR[Long]): GR[SkillRow] = GR {
    prs =>
      import prs._
      SkillRow(<<[Long])
  }
  /** Table description of table skill. Objects of this class serve as prototypes for rows in queries. */
  class Skill(_tableTag: Tag) extends Table[SkillRow](_tableTag, "skill") {
    def * = id <> (SkillRow, SkillRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = Rep.Some(id).shaped.<>(r => r.map(_ => SkillRow(r.get)), (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT), PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.PrimaryKey)

    /** Foreign key referencing Component (database name FK_a8eipu0i8qwgnn63bgj8e5iku) */
    lazy val componentFk = foreignKey("FK_a8eipu0i8qwgnn63bgj8e5iku", id, Component)(r => r.id, onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Skill */
  lazy val Skill = new TableQuery(tag => new Skill(tag))

  /**
   * Entity class storing rows of table User
   *  @param id Database column id SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param age Database column age SqlType(INT)
   *  @param description Database column description SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param email Database column email SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param firstName Database column first_name SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param lastName Database column last_name SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param password Database column password SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param photo Database column photo SqlType(LONGBLOB), Default(None)
   */
  case class UserRow(id: Long, age: Int, description: Option[String] = None, email: Option[String] = None, firstName: Option[String] = None, lastName: Option[String] = None, password: Option[String] = None, photo: Option[java.sql.Blob] = None)
  /** GetResult implicit for fetching UserRow objects using plain SQL queries */
  implicit def GetResultUserRow(implicit e0: GR[Long], e1: GR[Int], e2: GR[Option[String]], e3: GR[Option[java.sql.Blob]]): GR[UserRow] = GR {
    prs =>
      import prs._
      UserRow.tupled((<<[Long], <<[Int], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[java.sql.Blob]))
  }
  /** Table description of table user. Objects of this class serve as prototypes for rows in queries. */
  class User(_tableTag: Tag) extends Table[UserRow](_tableTag, "user") {
    def * = (id, age, description, email, firstName, lastName, password, photo) <> (UserRow.tupled, UserRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(age), description, email, firstName, lastName, password, photo).shaped.<>({ r => import r._; _1.map(_ => UserRow.tupled((_1.get, _2.get, _3, _4, _5, _6, _7, _8))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column age SqlType(INT) */
    val age: Rep[Int] = column[Int]("age")
    /** Database column description SqlType(VARCHAR), Length(255,true), Default(None) */
    val description: Rep[Option[String]] = column[Option[String]]("description", O.Length(255, varying = true), O.Default(None))
    /** Database column email SqlType(VARCHAR), Length(255,true), Default(None) */
    val email: Rep[Option[String]] = column[Option[String]]("email", O.Length(255, varying = true), O.Default(None))
    /** Database column first_name SqlType(VARCHAR), Length(255,true), Default(None) */
    val firstName: Rep[Option[String]] = column[Option[String]]("first_name", O.Length(255, varying = true), O.Default(None))
    /** Database column last_name SqlType(VARCHAR), Length(255,true), Default(None) */
    val lastName: Rep[Option[String]] = column[Option[String]]("last_name", O.Length(255, varying = true), O.Default(None))
    /** Database column password SqlType(VARCHAR), Length(255,true), Default(None) */
    val password: Rep[Option[String]] = column[Option[String]]("password", O.Length(255, varying = true), O.Default(None))
    /** Database column photo SqlType(LONGBLOB), Default(None) */
    val photo: Rep[Option[java.sql.Blob]] = column[Option[java.sql.Blob]]("photo", O.Default(None))

    /** Uniqueness Index over (email) (database name UK_ob8kqyqqgmefl0aco34akdtpe) */
    val index1 = index("UK_ob8kqyqqgmefl0aco34akdtpe", email, unique = true)
  }
  /** Collection-like TableQuery object for table User */
  lazy val User = new TableQuery(tag => new User(tag))

  /**
   * Entity class storing rows of table UserInfo
   *  @param id Database column id SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param content Database column content SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param orderOfView Database column order_of_view SqlType(INT)
   *  @param `type` Database column type SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param idUser Database column id_user SqlType(BIGINT), Default(None)
   */
  case class UserInfoRow(id: Long, content: Option[String] = None, orderOfView: Int, `type`: Option[String] = None, idUser: Option[Long] = None)
  /** GetResult implicit for fetching UserInfoRow objects using plain SQL queries */
  implicit def GetResultUserInfoRow(implicit e0: GR[Long], e1: GR[Option[String]], e2: GR[Int], e3: GR[Option[Long]]): GR[UserInfoRow] = GR {
    prs =>
      import prs._
      UserInfoRow.tupled((<<[Long], <<?[String], <<[Int], <<?[String], <<?[Long]))
  }
  /**
   * Table description of table user_info. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type
   */
  class UserInfo(_tableTag: Tag) extends Table[UserInfoRow](_tableTag, "user_info") {
    def * = (id, content, orderOfView, `type`, idUser) <> (UserInfoRow.tupled, UserInfoRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), content, Rep.Some(orderOfView), `type`, idUser).shaped.<>({ r => import r._; _1.map(_ => UserInfoRow.tupled((_1.get, _2, _3.get, _4, _5))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column content SqlType(VARCHAR), Length(255,true), Default(None) */
    val content: Rep[Option[String]] = column[Option[String]]("content", O.Length(255, varying = true), O.Default(None))
    /** Database column order_of_view SqlType(INT) */
    val orderOfView: Rep[Int] = column[Int]("order_of_view")
    /**
     * Database column type SqlType(VARCHAR), Length(255,true), Default(None)
     *  NOTE: The name was escaped because it collided with a Scala keyword.
     */
    val `type`: Rep[Option[String]] = column[Option[String]]("type", O.Length(255, varying = true), O.Default(None))
    /** Database column id_user SqlType(BIGINT), Default(None) */
    val idUser: Rep[Option[Long]] = column[Option[Long]]("id_user", O.Default(None))

    /** Foreign key referencing User (database name FK_mnhqyihjjfydarcg6yy2lpoya) */
    lazy val userFk = foreignKey("FK_mnhqyihjjfydarcg6yy2lpoya", idUser, User)(r => Rep.Some(r.id), onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table UserInfo */
  lazy val UserInfo = new TableQuery(tag => new UserInfo(tag))

  /**
   * Entity class storing rows of table WorkExperience
   *  @param description Database column description SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param place Database column place SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param title Database column title SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param yearFrom Database column year_from SqlType(INT)
   *  @param yearTo Database column year_to SqlType(INT)
   *  @param id Database column id SqlType(BIGINT), PrimaryKey
   */
  case class WorkExperienceRow(description: Option[String] = None, place: Option[String] = None, title: Option[String] = None, yearFrom: Int, yearTo: Int, id: Long)
  /** GetResult implicit for fetching WorkExperienceRow objects using plain SQL queries */
  implicit def GetResultWorkExperienceRow(implicit e0: GR[Option[String]], e1: GR[Int], e2: GR[Long]): GR[WorkExperienceRow] = GR {
    prs =>
      import prs._
      WorkExperienceRow.tupled((<<?[String], <<?[String], <<?[String], <<[Int], <<[Int], <<[Long]))
  }
  /** Table description of table work_experience. Objects of this class serve as prototypes for rows in queries. */
  class WorkExperience(_tableTag: Tag) extends Table[WorkExperienceRow](_tableTag, "work_experience") {
    def * = (description, place, title, yearFrom, yearTo, id) <> (WorkExperienceRow.tupled, WorkExperienceRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (description, place, title, Rep.Some(yearFrom), Rep.Some(yearTo), Rep.Some(id)).shaped.<>({ r => import r._; _4.map(_ => WorkExperienceRow.tupled((_1, _2, _3, _4.get, _5.get, _6.get))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column description SqlType(VARCHAR), Length(255,true), Default(None) */
    val description: Rep[Option[String]] = column[Option[String]]("description", O.Length(255, varying = true), O.Default(None))
    /** Database column place SqlType(VARCHAR), Length(255,true), Default(None) */
    val place: Rep[Option[String]] = column[Option[String]]("place", O.Length(255, varying = true), O.Default(None))
    /** Database column title SqlType(VARCHAR), Length(255,true), Default(None) */
    val title: Rep[Option[String]] = column[Option[String]]("title", O.Length(255, varying = true), O.Default(None))
    /** Database column year_from SqlType(INT) */
    val yearFrom: Rep[Int] = column[Int]("year_from")
    /** Database column year_to SqlType(INT) */
    val yearTo: Rep[Int] = column[Int]("year_to")
    /** Database column id SqlType(BIGINT), PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.PrimaryKey)

    /** Foreign key referencing Component (database name FK_sw03c0ljitehcflaw3wuuxwuv) */
    lazy val componentFk = foreignKey("FK_sw03c0ljitehcflaw3wuuxwuv", id, Component)(r => r.id, onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table WorkExperience */
  lazy val WorkExperience = new TableQuery(tag => new WorkExperience(tag))
}
