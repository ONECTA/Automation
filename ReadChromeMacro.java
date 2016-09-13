// Source : http://www.codeproject.com/Articles/330142/Cookie-Quest-A-Quest-to-Read-Cookies-from-Four-Pop

private static bool GetCookie_Chrome(string strHost, string strField, ref string Value)
{
    Value = string.Empty;
    bool fRtn = false;
    string strPath, strDb;

    // Check to see if Chrome Installed
    strPath = GetChromeCookiePath();
    if (string.Empty == strPath) // Nope, perhaps another browser
        return false;

    try
    {
        strDb = "Data Source=" + strPath + ";pooling=false";

        using (SQLiteConnection conn = new SQLiteConnection(strDb))
        {
            using (SQLiteCommand cmd = conn.CreateCommand())
            {
                cmd.CommandText = "SELECT value FROM cookies WHERE host_key LIKE '%" +
                    strHost + "%' AND name LIKE '%" + strField + "%';";

                conn.Open();
                using (SQLiteDataReader reader = cmd.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        Value = reader.GetString(0);
                        if (!Value.Equals(string.Empty))
                        {
                            fRtn = true;
                            break;
                        }
                    }
                }
                conn.Close();
            }
        }
    }
    catch (Exception)
    {
        Value = string.Empty;
        fRtn = false;
    }
    return fRtn;
}
