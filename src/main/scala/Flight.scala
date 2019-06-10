case class Flight(id: String,fldate: String,month:Integer, dofW: Integer, carrier: String, src: String,dst: String, crsdephour: Integer, crsdeptime: Integer, depdelay: Double, crsarrtime: Integer, arrdelay: Double, crselapsedtime: Double, dist: Double) {
    def showSourceDest() = {
      println("FLIGHT: "+id+"\nSOURCE: "+src+" DESTINATION: "+dst)
    }
}
