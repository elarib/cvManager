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
  lazy val schema: profile.SchemaDescription = Array(Competence.schema, CompetenceElt.schema, Education.schema, Objectif.schema, User.schema, WorkExperience.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /**
   * Entity class storing rows of table Competence
   *  @param id Database column id SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param userId Database column user_id SqlType(BIGINT)
   */
  case class CompetenceRow(id: Long, name: Option[String] = None, userId: Long)
  /** GetResult implicit for fetching CompetenceRow objects using plain SQL queries */
  implicit def GetResultCompetenceRow(implicit e0: GR[Long], e1: GR[Option[String]]): GR[CompetenceRow] = GR {
    prs =>
      import prs._
      CompetenceRow.tupled((<<[Long], <<?[String], <<[Long]))
  }
  /** Table description of table competence. Objects of this class serve as prototypes for rows in queries. */
  class Competence(_tableTag: Tag) extends Table[CompetenceRow](_tableTag, "competence") {
    def * = (id, name, userId) <> (CompetenceRow.tupled, CompetenceRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), name, Rep.Some(userId)).shaped.<>({ r => import r._; _1.map(_ => CompetenceRow.tupled((_1.get, _2, _3.get))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(VARCHAR), Length(255,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("name", O.Length(255, varying = true), O.Default(None))
    /** Database column user_id SqlType(BIGINT) */
    val userId: Rep[Long] = column[Long]("user_id")

    /** Foreign key referencing User (database name fk_competence_user1) */
    lazy val userFk = foreignKey("fk_competence_user1", userId, User)(r => r.id, onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Competence */
  lazy val Competence = new TableQuery(tag => new Competence(tag))

  /**
   * Entity class storing rows of table CompetenceElt
   *  @param id Database column id SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param detail Database column detail SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param name Database column name SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param competenceId Database column competence_id SqlType(BIGINT)
   */
  case class CompetenceEltRow(id: Long, detail: Option[String] = None, name: Option[String] = None, competenceId: Long)
  /** GetResult implicit for fetching CompetenceEltRow objects using plain SQL queries */
  implicit def GetResultCompetenceEltRow(implicit e0: GR[Long], e1: GR[Option[String]]): GR[CompetenceEltRow] = GR {
    prs =>
      import prs._
      CompetenceEltRow.tupled((<<[Long], <<?[String], <<?[String], <<[Long]))
  }
  /** Table description of table competence_elt. Objects of this class serve as prototypes for rows in queries. */
  class CompetenceElt(_tableTag: Tag) extends Table[CompetenceEltRow](_tableTag, "competence_elt") {
    def * = (id, detail, name, competenceId) <> (CompetenceEltRow.tupled, CompetenceEltRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), detail, name, Rep.Some(competenceId)).shaped.<>({ r => import r._; _1.map(_ => CompetenceEltRow.tupled((_1.get, _2, _3, _4.get))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column detail SqlType(VARCHAR), Length(255,true), Default(None) */
    val detail: Rep[Option[String]] = column[Option[String]]("detail", O.Length(255, varying = true), O.Default(None))
    /** Database column name SqlType(VARCHAR), Length(255,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("name", O.Length(255, varying = true), O.Default(None))
    /** Database column competence_id SqlType(BIGINT) */
    val competenceId: Rep[Long] = column[Long]("competence_id")

    /** Foreign key referencing Competence (database name fk_element_competence_competence1) */
    lazy val competenceFk = foreignKey("fk_element_competence_competence1", competenceId, Competence)(r => r.id, onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table CompetenceElt */
  lazy val CompetenceElt = new TableQuery(tag => new CompetenceElt(tag))

  /**
   * Entity class storing rows of table Education
   *  @param id Database column id SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param description Database column description SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param place Database column place SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param yearFrom Database column year_from SqlType(INT)
   *  @param yearTo Database column year_to SqlType(INT)
   *  @param userId Database column user_id SqlType(BIGINT)
   */
  case class EducationRow(id: Long, description: Option[String] = None, place: Option[String] = None, yearFrom: Int, yearTo: Int, userId: Long)
  /** GetResult implicit for fetching EducationRow objects using plain SQL queries */
  implicit def GetResultEducationRow(implicit e0: GR[Long], e1: GR[Option[String]], e2: GR[Int]): GR[EducationRow] = GR {
    prs =>
      import prs._
      EducationRow.tupled((<<[Long], <<?[String], <<?[String], <<[Int], <<[Int], <<[Long]))
  }
  /** Table description of table education. Objects of this class serve as prototypes for rows in queries. */
  class Education(_tableTag: Tag) extends Table[EducationRow](_tableTag, "education") {
    def * = (id, description, place, yearFrom, yearTo, userId) <> (EducationRow.tupled, EducationRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), description, place, Rep.Some(yearFrom), Rep.Some(yearTo), Rep.Some(userId)).shaped.<>({ r => import r._; _1.map(_ => EducationRow.tupled((_1.get, _2, _3, _4.get, _5.get, _6.get))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column description SqlType(VARCHAR), Length(255,true), Default(None) */
    val description: Rep[Option[String]] = column[Option[String]]("description", O.Length(255, varying = true), O.Default(None))
    /** Database column place SqlType(VARCHAR), Length(255,true), Default(None) */
    val place: Rep[Option[String]] = column[Option[String]]("place", O.Length(255, varying = true), O.Default(None))
    /** Database column year_from SqlType(INT) */
    val yearFrom: Rep[Int] = column[Int]("year_from")
    /** Database column year_to SqlType(INT) */
    val yearTo: Rep[Int] = column[Int]("year_to")
    /** Database column user_id SqlType(BIGINT) */
    val userId: Rep[Long] = column[Long]("user_id")

    /** Foreign key referencing User (database name fk_education_user1) */
    lazy val userFk = foreignKey("fk_education_user1", userId, User)(r => r.id, onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Education */
  lazy val Education = new TableQuery(tag => new Education(tag))

  /**
   * Entity class storing rows of table Objectif
   *  @param id Database column id SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param content Database column content SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param userId Database column user_id SqlType(BIGINT)
   */
  case class ObjectifRow(id: Long, content: Option[String] = None, userId: Long)
  /** GetResult implicit for fetching ObjectifRow objects using plain SQL queries */
  implicit def GetResultObjectifRow(implicit e0: GR[Long], e1: GR[Option[String]]): GR[ObjectifRow] = GR {
    prs =>
      import prs._
      ObjectifRow.tupled((<<[Long], <<?[String], <<[Long]))
  }
  /** Table description of table objectif. Objects of this class serve as prototypes for rows in queries. */
  class Objectif(_tableTag: Tag) extends Table[ObjectifRow](_tableTag, "objectif") {
    def * = (id, content, userId) <> (ObjectifRow.tupled, ObjectifRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), content, Rep.Some(userId)).shaped.<>({ r => import r._; _1.map(_ => ObjectifRow.tupled((_1.get, _2, _3.get))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column content SqlType(VARCHAR), Length(255,true), Default(None) */
    val content: Rep[Option[String]] = column[Option[String]]("content", O.Length(255, varying = true), O.Default(None))
    /** Database column user_id SqlType(BIGINT) */
    val userId: Rep[Long] = column[Long]("user_id")

    /** Foreign key referencing User (database name fk_objectif_user1) */
    lazy val userFk = foreignKey("fk_objectif_user1", userId, User)(r => r.id, onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Objectif */
  lazy val Objectif = new TableQuery(tag => new Objectif(tag))

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
   * Entity class storing rows of table WorkExperience
   *  @param id Database column id SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param description Database column description SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param place Database column place SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param title Database column title SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param yearFrom Database column year_from SqlType(INT)
   *  @param yearTo Database column year_to SqlType(INT)
   *  @param userId Database column user_id SqlType(BIGINT)
   */
  case class WorkExperienceRow(id: Long, description: Option[String] = None, place: Option[String] = None, title: Option[String] = None, yearFrom: Int, yearTo: Int, userId: Long)
  /** GetResult implicit for fetching WorkExperienceRow objects using plain SQL queries */
  implicit def GetResultWorkExperienceRow(implicit e0: GR[Long], e1: GR[Option[String]], e2: GR[Int]): GR[WorkExperienceRow] = GR {
    prs =>
      import prs._
      WorkExperienceRow.tupled((<<[Long], <<?[String], <<?[String], <<?[String], <<[Int], <<[Int], <<[Long]))
  }
  /** Table description of table work_experience. Objects of this class serve as prototypes for rows in queries. */
  class WorkExperience(_tableTag: Tag) extends Table[WorkExperienceRow](_tableTag, "work_experience") {
    def * = (id, description, place, title, yearFrom, yearTo, userId) <> (WorkExperienceRow.tupled, WorkExperienceRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), description, place, title, Rep.Some(yearFrom), Rep.Some(yearTo), Rep.Some(userId)).shaped.<>({ r => import r._; _1.map(_ => WorkExperienceRow.tupled((_1.get, _2, _3, _4, _5.get, _6.get, _7.get))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
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
    /** Database column user_id SqlType(BIGINT) */
    val userId: Rep[Long] = column[Long]("user_id")

    /** Foreign key referencing User (database name fk_work_experience_user1) */
    lazy val userFk = foreignKey("fk_work_experience_user1", userId, User)(r => r.id, onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table WorkExperience */
  lazy val WorkExperience = new TableQuery(tag => new WorkExperience(tag))
}
