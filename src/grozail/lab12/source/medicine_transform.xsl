<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output omit-xml-declaration="yes"/>
    <xsl:template match="/">
        <html>
            <head>
                <meta charset = "utf-8">

                </meta>
            </head>
            <body>
                <table border="1">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Dosage</th>
                            <th>Color</th>
                            <th>Body</th>
                            <th>Indications</th>
                        </tr>
                    </thead>
                    <xsl:for-each select="medicine_list/medicine">
                        <tr>
                            <xsl:call-template name="print_medicine"/>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

    <xsl:template name="print_medicine">
        <td>
            <xsl:value-of select="name"/>
        </td>
        <td>
            <xsl:value-of select="price"/>
        </td>
        <td>
            <xsl:value-of select="dosage"/>
        </td>
        <td>
            <xsl:value-of select="visual/color"/>
        </td>
        <td>
            <xsl:value-of select="visual/body"/>
        </td>
        <td>
            <xsl:value-of select="visual/indications"/>
        </td>
    </xsl:template>
</xsl:stylesheet>